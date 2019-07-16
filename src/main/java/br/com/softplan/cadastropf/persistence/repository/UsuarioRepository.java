package br.com.softplan.cadastropf.persistence.repository;

import br.com.softplan.cadastropf.persistence.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
