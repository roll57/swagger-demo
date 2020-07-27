package rolth.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RequestMapping("/demo")
@RestController
public class MyRestController {

    @GetMapping(produces = TEXT_PLAIN_VALUE)
    public String get() {
        return "some text";
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public JsonResponse getJson() {
        return new JsonResponse();
    }

    @GetMapping(produces = HAL_JSON_VALUE)
    public EntityModel<JsonResponse> getHal() {
        return EntityModel.of(new JsonResponse(),
            linkTo(MyRestController.class).slash("somelink").withSelfRel()
        );
    }
}
