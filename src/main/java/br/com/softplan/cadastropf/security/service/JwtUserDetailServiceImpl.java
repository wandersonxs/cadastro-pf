package br.com.softplan.cadastropf.security.service;

import br.com.softplan.cadastropf.persistence.model.Usuario;
import br.com.softplan.cadastropf.security.JwtUserFactory;
import br.com.softplan.cadastropf.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioService.findByEmail(email);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado " + email);
        }else{
            return JwtUserFactory.create(usuario);
        }
    }
}
