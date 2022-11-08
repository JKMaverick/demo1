package com.example.demo1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return ResponseEntity.of(Optional.of(String.format("Hello %s!", name)));
    }

}
