package br.com.softplan.cadastropf.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class SourceResource {

    @ApiOperation(value = "Sources", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 404, message = "Não localizada")})
    @GetMapping(value = "/source", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, String> getSources() {

        Map<String, String> sourcesMap = new HashMap<String, String>();
        sourcesMap.put("frontend", "https://github.com/wandersonxs/cadastro-front");
        sourcesMap.put("backend", "https://github.com/wandersonxs/cadastro-pf");
        return sourcesMap;
    }
}