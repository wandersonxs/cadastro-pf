package br.com.softplan.cadastropf.security.controller;



import br.com.softplan.cadastropf.persistence.model.Usuario;
import br.com.softplan.cadastropf.security.JwtAuthenticationRequest;
import br.com.softplan.cadastropf.security.JwtTokenUtil;
import br.com.softplan.cadastropf.security.model.CurrentUser;
import br.com.softplan.cadastropf.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Criar token
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/api/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception{
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final Usuario usuario = usuarioService.findByEmail(authenticationRequest.getEmail());
        usuario.setPassword(null);
        return ResponseEntity.ok(new CurrentUser(token, usuario));
    }

    /**
     * Atualizar token.
     * @param request
     * @return
     */
    @PostMapping(value = "/api/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = jwtTokenUtil.getUserNameFromToken(token);
        final Usuario usuario = usuarioService.findByEmail(username);

        if(jwtTokenUtil.canTokenBeRefreshed(token)){
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new CurrentUser(refreshedToken, usuario));
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

}
