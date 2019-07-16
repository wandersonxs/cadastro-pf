package br.com.softplan.cadastropf.service;

import br.com.softplan.cadastropf.persistence.model.Usuario;

public interface UsuarioService {
    Usuario findByEmail(String email);
}
