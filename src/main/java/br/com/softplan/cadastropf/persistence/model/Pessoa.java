package br.com.softplan.cadastropf.persistence.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.GroupSequence;
import javax.validation.constraints.*;
import java.util.Date;

@GroupSequence({Pessoa.class})
@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "[nome] é obrigatório!")
    @NotNull(message = "[nome] é obrigatório!")
    @Column(name = "nome", length = 100)
    private String nome;

    @Pattern(regexp = "M|F|O", message = "[sexo] inválido! Valores permitidos M, F ou O.")
    @Column(name = "sexo")
    private String sexo;

    @Email(message = "[email] inválido!")
    @Column(name = "email")
    private String email;

    @Past(message = "[dataNascimento] precisa ser uma data no passado!")
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_nascimento")
    private Date dataNascimento;

    @Column(name = "naturalidade")
    private String naturalidade;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @CPF
    @NotBlank(message = "[cpf] é obrigatório!")
    @NotNull(message = "[cpf] é obrigatório!")
    @Column(name = "cpf")
    private String cpf;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_criacao")
    private Date dataCriacao = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_atualizacao")
    private Date dataAtualizacao;

}
