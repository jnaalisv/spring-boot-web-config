package example.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ExampleController {

    private final DateTimeService dateTimeService;

    public ExampleController(final DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExampleDetail get() {
        ExampleDetail exampleDetail = new ExampleDetail();
        exampleDetail.dateTime = dateTimeService.getLocalDateTime();
        return exampleDetail;
    }

}
