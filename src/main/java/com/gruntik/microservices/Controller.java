package com.gruntik.microservices;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @GetMapping(path = "/date", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPersonsCacheCount() {
        System.out.println("hello");
        return LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }

}
