package br.com.softplan.cadastropf.resource;


import br.com.softplan.cadastropf.exception.CadastroException;
import br.com.softplan.cadastropf.exception.PessoaNotFoundException;
import br.com.softplan.cadastropf.persistence.model.Pessoa;
import br.com.softplan.cadastropf.service.PessoaService;
import br.com.softplan.cadastropf.util.DateUtil;
import br.com.softplan.cadastropf.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/v1")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @ApiOperation(value = "Criar Pessoa", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created", response = Pessoa.class),
            @ApiResponse(code = 500, message = "Erro interno do sistema"),
            @ApiResponse(code = 409, message = "Cpf duplicado") /* Algumas APIS usam 422 */,
            @ApiResponse(code = 400, message = "Erro nos parametros no request")})
    @PostMapping(value = "/pessoas", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Pessoa> savePessoa(@Valid @RequestBody final Pessoa pessoa) {
        String cpf = StringUtil.getNumbers(pessoa.getCpf());
        if (pessoaService.findByCpf(cpf) != null)
            throw new CadastroException("Cpf já cadastrado.");
        pessoa.setCpf(cpf);
        Pessoa pessoaCreated = pessoaService.save(pessoa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaCreated.getId()).toUri();
        return ResponseEntity.created(location).body(pessoaCreated);
    }


    @ApiOperation(value = "Atualizar Pessoa", httpMethod = "PUT")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created", response = Pessoa.class),
            @ApiResponse(code = 500, message = "Erro interno do sistema"),
            @ApiResponse(code = 404, message = "Pessoa não localizada"),
            @ApiResponse(code = 400, message = "Erro nos parametros no request")})
    @PutMapping(value = "/pessoas/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Pessoa> updatePessoa(@Valid @RequestBody final Pessoa pessoa, @PathVariable final Long id) {
        Pessoa pesssoaUpdated = pessoaService.findById(id);
        if (pesssoaUpdated == null) {
            throw new PessoaNotFoundException("id:" + id);
        } else {
            pessoa.setDataAtualizacao(new Date());
            pessoaService.save(pessoa);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                    .body(pessoaService.save(pessoa));
        }
    }

    @ApiOperation(value = "Localizar Pessoa", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 404, message = "Pessoa não localizada"),
            @ApiResponse(code = 500, message = "Erro interno do sistema")})
    @GetMapping(value = "/pessoas/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Pessoa getPessoa(@PathVariable final Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        if (pessoa == null) {
            throw new PessoaNotFoundException("id:" + id);
        }
        return pessoa;
    }

    @ApiOperation(value = "Delete Pessoa", httpMethod = "DELETE")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Pessoa Excluída."),
            @ApiResponse(code = 404, message = "Pessoa não localizada"),
            @ApiResponse(code = 500, message = "Erro interno do sistema")})
    @DeleteMapping(value = "/pessoas/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePessoa(@PathVariable final Long id) {
        boolean deleted = pessoaService.deleteById(id);
        if (!deleted) {
            throw new PessoaNotFoundException("id:" + id);
        }
    }

    @ApiOperation(value = "Localizar Pessoa", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 404, message = "Pessoa não localizada"),
            @ApiResponse(code = 500, message = "Erro interno do sistema")})
    @GetMapping(value = "/pessoas", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Pessoa> getPessoas(@RequestParam(required = false) Long id,
                                   @RequestParam(required = false) String nome,
                                   @RequestParam(required = false) String sexo,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String dataNascimento,
                                   @RequestParam(required = false) String naturalidade,
                                   @RequestParam(required = false) String nacionalidade,
                                   @RequestParam(required = false) String cpf
    ) {


        Date dataNasc = DateUtil.stringToDateOnly(dataNascimento);
        cpf = StringUtil.getNumbers(cpf);

        List<Pessoa> pessoas = pessoaService.findByAllParams(id, nome, sexo, email, dataNasc, naturalidade, nacionalidade, cpf);
        if (pessoas.isEmpty()) {
            throw new PessoaNotFoundException("Não há Pessoa com os parâmetros informados.");
        }
        return pessoas;
    }
}