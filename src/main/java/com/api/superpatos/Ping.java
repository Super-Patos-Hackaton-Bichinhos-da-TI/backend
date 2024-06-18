package com.api.superpatos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ping")
public class Ping {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public void ping() {
        System.out.println("pong");
    }
}
