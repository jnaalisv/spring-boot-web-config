package example.controllers;

import example.config.ExampleWebMvcConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ExampleWebMvcConfiguration.class})
@RunWith(SpringRunner.class)
public class ExampleControllerIntegrationTest {

    @MockBean
    private DateTimeService dateTimeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void equalUserNameAndPasswordAuthenticate() throws Exception {
        given(this.dateTimeService.getLocalDateTime())
                .willReturn(LocalDateTime.of(2017, 6, 20, 23,11));

        mockMvc
                .perform(get("/api").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string("{\n  \"dateTime\" : \"2017-06-20T23:11:00\"\n}"));
    }

}
