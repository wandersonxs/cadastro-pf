package br.com.softplan.cadastropf.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtAuthenticationRequest implements Serializable {

    private String email;
    private String password;

    public JwtAuthenticationRequest(){
        super();
    }

    public JwtAuthenticationRequest(String email, String password){
        this.setEmail(email);
        this.setPassword(password);
    }

}
