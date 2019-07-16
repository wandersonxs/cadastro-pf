package br.com.softplan.cadastropf;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
@ActiveProfiles("dev")
public class ApplicationTest {

    //HTTPCODE
    protected int HTTP_CODE_OK = 200;
    protected int HTTP_CODE_CREATED = 201;
    protected int HTTP_CODE_NOT_FOUND = 404;
    protected int HTTP_CODE_CONFLICT = 409;
    protected int HTTP_CODE_BAD_REQUEST = 400;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * Certificar que mockMvn foi gerado com sucesso.
     */
    @Test
    public void mockMvcNotNullTest(){
        assertTrue(mockMvc != null);
    }

    /**
     * Metódo Request Genérico
     *
     * @param httpMethod
     * @param url_resource
     * @param resource
     * @return
     * @throws Exception
     */
    private MvcResult request(HttpMethod httpMethod, String url_resource, Object resource) throws Exception {
        MvcResult result = null;
        switch (httpMethod) {
            case POST:
                result = mockMvc.perform(MockMvcRequestBuilders.post(url_resource)
                        .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(resource))
                        .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
                break;
            case PUT:
                result = mockMvc.perform(MockMvcRequestBuilders.put(url_resource)
                        .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(resource))
                        .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
                break;
            case GET:
                result = mockMvc.perform(MockMvcRequestBuilders.get(url_resource)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
                break;
            case DELETE:
                result = mockMvc.perform(MockMvcRequestBuilders.delete(url_resource)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
                break;
            default:
                System.out.println("httpMethod não disponível");
                return null;
        }
        return result;
    }

    /**
     * POST REQUEST
     *
     * @param url_resource
     * @param resource
     * @return
     * @throws Exception
     */
    public MvcResult post(String url_resource, Object resource) throws Exception {
        return request(HttpMethod.POST, url_resource, resource);
    }

    /**
     * PUT REQUEST
     *
     * @param url_resource
     * @param resource
     * @return
     * @throws Exception
     */
    public MvcResult put(String url_resource, Object resource) throws Exception {
        return request(HttpMethod.PUT, url_resource, resource);
    }

    /**
     * DELETE REQUEST
     *
     * @param url_resource
     * @return
     * @throws Exception
     */
    public MvcResult delete(String url_resource) throws Exception {
        return request(HttpMethod.DELETE, url_resource, null);
    }

    /**
     * GET REQUEST
     *
     * @param url_resource
     * @return
     * @throws Exception
     */
    public MvcResult get(String url_resource) throws Exception {
        return request(HttpMethod.GET, url_resource, null);
    }

    /**
     * Object to JSON STRING
     *
     * @param obj
     * @return
     */
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}