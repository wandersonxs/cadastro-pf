package br.com.softplan.cadastropf.service;


import br.com.softplan.cadastropf.persistence.model.Pessoa;
import br.com.softplan.cadastropf.persistence.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return  pessoaRepository.save(pessoa);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional p = pessoaRepository.findById(id);
        if( p.isPresent()){
            pessoaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Pessoa findById(Long id) {

        Optional p = pessoaRepository.findById(id);

        if( p.isPresent()){
            return (Pessoa) p.get();
        }
        return null;
    }

    @Override
    public Pessoa findByCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    @Override
    public List<Pessoa> findByAllParams(Long id, String nome, String sexo, String email, Date dataNascimento, String naturalidade, String nacionalidade, String cpf) {
        return pessoaRepository.findByAllParams(id, nome, sexo,email,dataNascimento,naturalidade, nacionalidade, cpf);
    }

}
