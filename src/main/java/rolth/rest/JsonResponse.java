package rolth.rest;

import lombok.Data;
import springfox.documentation.spring.web.json.Json;

@Data
public class JsonResponse {

    private String value;
    private String jsonValue;

    public JsonResponse() {
        value = "someValue";
        jsonValue = "ok";
    }
}
