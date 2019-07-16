package br.com.softplan.cadastropf.resource;

import br.com.softplan.cadastropf.Application;
import br.com.softplan.cadastropf.ApplicationTest;
import br.com.softplan.cadastropf.persistence.model.Pessoa;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PessoaResourceTest extends ApplicationTest {

    private final String URL_RESOURCE = "/v1/pessoas";
    private String email_test = "wandersonxs@yahoo.com.br";
    private static Pessoa pessoa_teste;

    /**
     * Criação de Pessoa com Nome em branco
     *
     * @throws Exception
     */
    @Test
    public void a_createPessoaNomeEmptyTest() throws Exception {
        Pessoa pessoa = getNovaPessoa();
        pessoa.setNome("");
        MvcResult result = post(URL_RESOURCE, pessoa);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Criação de Pessoa com Nome em branco
     *
     * @throws Exception
     */
    @Test
    public void b_createPessoaNomeNullTest() throws Exception {
        Pessoa pessoa = getNovaPessoa();
        pessoa.setNome(null);
        MvcResult result = post(URL_RESOURCE, pessoa);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Criação de Pessoa com Email inválido
     *
     * @throws Exception
     */
    @Test
    public void c_createPessoaEmailWrongFormatTest() throws Exception {
        Pessoa pessoa = getNovaPessoa();
        pessoa.setEmail("wan.");
        MvcResult result = post(URL_RESOURCE, pessoa);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Criação de Pessoa com data de nascimento inválida
     *
     * @throws Exception
     */
    @Test
    public void d_createPessoaDataNascimentoInvalidTest() throws Exception {
        Pessoa pessoa = getNovaPessoa();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2100);
        pessoa.setDataNascimento(c.getTime());
        MvcResult result = post(URL_RESOURCE, pessoa);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Criação de Pessoa com cpf inválido
     *
     * @throws Exception
     */
    @Test
    public void e_createPessoaInvalidCpf() throws Exception {
        Pessoa pessoa = getNovaPessoa();
        pessoa.setCpf("123");
        MvcResult result = post(URL_RESOURCE, pessoa);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Teste Criação de Pessoa
     *
     * @throws Exception
     */
    @Test
    public void f_createPessoaTest() throws Exception {
        Pessoa pessoa = getNovaPessoa();
        MvcResult result = post(URL_RESOURCE, pessoa);
        String content = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Pessoa ps = mapper.readValue(content, Pessoa.class);
        pessoa_teste = ps;
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_CREATED);
    }

    /**
     * Teste Criação de Pessoa com cpf já cadastrado
     *
     * @throws Exception
     */
    @Test
    public void g_createPessoaCpfCadastradoTest() throws Exception {
        Pessoa pessoa = getNovaPessoa();
        MvcResult result = post(URL_RESOURCE, pessoa);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_CONFLICT);
    }

    /**
     * Teste Atualização Pessoa
     *
     * @throws Exception
     */
    @Test
    public void h_updatePessoaTest() throws Exception {
        pessoa_teste.setNome("Wanderson Update");
        MvcResult result = put(URL_RESOURCE + "/" + pessoa_teste.getId(), pessoa_teste);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_OK);
    }

    /**
     * Atualização de Pessoa Nome em branco
     *
     * @throws Exception
     */
    @Test
    public void i_updateEmptyNomePessoaTest() throws Exception {
        pessoa_teste.setNome("");
        MvcResult result = put(URL_RESOURCE + "/" + pessoa_teste.getId(), pessoa_teste);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Atualização de Pessoa com Nome em branco
     *
     * @throws Exception
     */
    @Test
    public void j_updatePessoaNomeNullTest() throws Exception {
        pessoa_teste.setNome(null);
        MvcResult result = put(URL_RESOURCE + "/" + pessoa_teste.getId(), pessoa_teste);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Atualização de Pessoa com Email inválido
     *
     * @throws Exception
     */
    @Test
    public void k_updatePessoaEmailWrongFormatTest() throws Exception {
        pessoa_teste.setEmail("wan.");
        MvcResult result = put(URL_RESOURCE + "/" + pessoa_teste.getId(), pessoa_teste);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Atualização de Pessoa com data de nascimento inválida
     *
     * @throws Exception
     */
    @Test
    public void l_updatePessoaDataNascimentoInvalidTest() throws Exception {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2100);
        pessoa_teste.setDataNascimento(c.getTime());
        MvcResult result = put(URL_RESOURCE + "/" + pessoa_teste.getId(), pessoa_teste);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Atualização de Pessoa com cpf inválido
     *
     * @throws Exception
     */
    @Test
    public void m_updatePessoaInvalidCpf() throws Exception {
        pessoa_teste.setCpf("123");
        MvcResult result = put(URL_RESOURCE + "/" + pessoa_teste.getId(), pessoa_teste);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_BAD_REQUEST);
    }

    /**
     * Pesquisa por Cpf bem sucedida
     *
     * @throws Exception
     */
    @Test
    public void n_getPessoaByCpfTest() throws Exception {
        String params = "?cpf=268.233.408.35";
        MvcResult result = get(URL_RESOURCE + params);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_OK);
    }

    /**
     * Pesquisa por Id
     *
     * @throws Exception
     */
    @Test
    public void o_getPessoaById() throws Exception {
        MvcResult result = get(URL_RESOURCE + "/" + pessoa_teste.getId());
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_OK);
    }

    /**
     * Delete Pessoa
     *
     * @throws Exception
     */
    @Test
    public void p_deletePessoaTest() throws Exception {
        MvcResult result = delete(URL_RESOURCE + "/" + pessoa_teste.getId());
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_OK);
    }


    /**
     * Pesquisa por Cpf NOT FOUND
     *
     * @throws Exception
     */
    @Test
    public void q_getPessoaByCpfNotFoundTest() throws Exception {
        String params = "?cpf=268.233.408.35";
        MvcResult result = get(URL_RESOURCE + params);
        Assert.assertTrue(result.getResponse().getStatus() == HTTP_CODE_NOT_FOUND);
    }

    /**
     * Pessoa MOCK
     * @return
     */
    private Pessoa getNovaPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("268.233.408-35");
        pessoa.setNome("Wanderson");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1990);
        pessoa.setDataNascimento(c.getTime());
        pessoa.setEmail(email_test);
        pessoa.setNaturalidade("Jacareí");
        pessoa.setNacionalidade("Brasil");
        pessoa.setSexo("M");
        return pessoa;
    }

}
