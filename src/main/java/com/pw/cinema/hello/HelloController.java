package com.pw.cinema.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(path="/")
    public String getHello(){
        return "Hello world!";
    }
}
