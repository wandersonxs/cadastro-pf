package br.com.softplan.cadastropf.service;

import br.com.softplan.cadastropf.persistence.model.Pessoa;

import java.util.Date;
import java.util.List;

public interface PessoaService {

    Pessoa save(Pessoa pessoa);

    boolean deleteById(Long id);

    Pessoa findById(Long id);

    Pessoa findByCpf(String cpf);

    List<Pessoa> findByAllParams(Long id, String nome, String sexo, String email, Date dataNascimento, String naturalidade, String nacionalidade, String cpf);
}
