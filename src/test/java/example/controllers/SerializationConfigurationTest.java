package example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.config.SerializationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {SerializationConfig.class})
@RunWith(SpringRunner.class)
public class SerializationConfigurationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDateTimeSerialization() throws JsonProcessingException {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 20, 23,11);
        String expected = "\"2017-06-20T23:11:00\"";

        String stringValue = objectMapper.writeValueAsString(dateTime);

        assertThat(stringValue).isEqualTo(expected);
    }
}
