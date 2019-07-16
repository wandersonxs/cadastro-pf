package br.com.softplan.cadastropf.security.model;

import br.com.softplan.cadastropf.persistence.model.Usuario;
import lombok.Data;

@Data
public class CurrentUser {

    private String token;
    private Usuario usuario;

    public CurrentUser(String token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
    }
}
