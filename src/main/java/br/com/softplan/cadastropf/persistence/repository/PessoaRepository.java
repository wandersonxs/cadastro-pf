package br.com.softplan.cadastropf.persistence.repository;

import br.com.softplan.cadastropf.persistence.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    /**
     * Pesquisa por cpf.
     * @param cpf
     * @return
     */
    Pessoa findByCpf(String cpf);

    /**
     * Pesquisa por todos os parâmetros de Pessoa.
     * @param id
     * @param nome
     * @param sexo
     * @param email
     * @param dataNascimento
     * @param naturalidade
     * @param nacionalidade
     * @param cpf
     * @return
     */
    @Query(value = "select * from pessoa where " +
            " (:id is null or id = :id ) and " +
            " (:nome is null or nome = :nome ) and " +
            " (:sexo is null or sexo = :sexo ) and  " +
            " (:email is null or email = :email ) and  " +
            " (:dataNascimento is null or dt_nascimento = :dataNascimento ) and  " +
            " (:naturalidade is null or naturalidade = :naturalidade ) and  " +
            " (:nacionalidade is null or nacionalidade = :nacionalidade ) and  " +
            " (:cpf is null or cpf = :cpf )", nativeQuery = true)
    List<Pessoa> findByAllParams(@Param("id") Long id,
                                 @Param("nome") String nome,
                                 @Param("sexo") String sexo,
                                 @Param("email") String email,
                                 @Param("dataNascimento") Date dataNascimento,
                                 @Param("naturalidade") String naturalidade,
                                 @Param("nacionalidade") String nacionalidade,
                                 @Param("cpf") String cpf);

}