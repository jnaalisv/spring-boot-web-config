package example.controllers;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateTimeService {

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

}
