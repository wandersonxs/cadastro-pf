package br.com.softplan.cadastropf.persistence.model;

import br.com.softplan.cadastropf.persistence.enums.ProfileEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    private ProfileEnum profile;

}
