package example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import example.config.SerializationConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ExampleControllerTest {

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private ExampleController productController;

    private MockMvc mvc;
    private static final ObjectMapper objectMapper  = new SerializationConfig().objectMapper();

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(productController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    public void localDateTimeShouldSerializeCorrectly() throws Exception {
        given(this.dateTimeService.getLocalDateTime())
                .willReturn(LocalDateTime.of(2017, 6, 20, 23,11));

        this.mvc.perform(
                get("/api").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("{\n  \"dateTime\" : \"2017-06-20T23:11:00\"\n}"));
    }
}
