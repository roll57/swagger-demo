package rolth;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSwaggerResponse {

    @LocalServerPort
    protected int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testApiDocsContainsJson() {
        given()
            .get("v3/api-docs")
            .then()
            .body("paths.'/demo'.get.responses.'200'.content.'application/json'.schema.$ref", is(equalTo("#/components/schemas/JsonResponse")));
    }

    @Test
    public void testApiDocsContainsHalJson() {
        given()
            .get("v3/api-docs")
            .then()
            .body("paths.'/demo'.get.responses.'200'.content.'application/hal+json'.schema.$ref", is(equalTo("#/components/schemas/EntityModel«JsonResponse»")));
    }

    @Test
    public void testApiDocsContainsString() {
        given()
            .get("v3/api-docs")
            .then()
            .body("paths.'/demo'.get.responses.'200'.content.'text/plain'.schema.type", is(equalTo("string")));
    }

}
