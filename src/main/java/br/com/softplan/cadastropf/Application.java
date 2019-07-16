package br.com.softplan.cadastropf;

import br.com.softplan.cadastropf.persistence.enums.ProfileEnum;
import br.com.softplan.cadastropf.persistence.model.Usuario;
import br.com.softplan.cadastropf.persistence.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={"br.com.softplan.cadastropf"})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
    CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        return args -> {
            initUsuario(usuarioRepository, passwordEncoder);
        };
    }

    /**
     * A propósito de facilitar o teste do desafio, crio um usuário caso não exista um
     * @param usuarioRepository
     * @param passwordEncoder
     */
	private void initUsuario(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        Usuario admin = new Usuario();
        admin.setEmail("admin@softplan.com.br");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setProfile(ProfileEnum.ROLE_ADMIN);
        Usuario usuario = usuarioRepository.findByEmail("admin@softplan.com.br");
        if(usuario == null){
            usuarioRepository.save(admin);
        }
    }
}
