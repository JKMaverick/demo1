package com.example.demo1;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

@Controller
@RequestMapping("translations")
public class TranslationsController {

    private String externalApiURL = "https://api.dictionaryapi.dev/api/v2/entries/en/";


    @GetMapping
    public ResponseEntity<String> translations(@RequestParam(value = "word") String word) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(externalApiURL + word, String.class);
        return response;
    }

    @GetMapping("v2")
    public ResponseEntity<Word[]> translationsV2(@RequestParam(value = "word") String word) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Word[]> response = restTemplate.getForEntity(externalApiURL + word, Word[].class);
        Word[] body = response.getBody();
        Word word2 = body[0];
        return response;
    }

    @GetMapping("v3")
    public String translationsV3(@RequestParam(value = "word") String word, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Word[]> response = restTemplate.getForEntity(externalApiURL + word, Word[].class);
        Word[] body = response.getBody();
        Word word2 = body[0];
        model.addAttribute(word2); // word
        model.addAttribute("word2", word2);
        return "index.html";
    }

    @GetMapping("start")
    public String startPage(){
        return "start";
    }

    @PostMapping("start")
    public String resultPage(Model model, @RequestParam String word){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Word[]> response = restTemplate.getForEntity(externalApiURL + word, Word[].class);
        Word[] body = response.getBody();
        Word word1 = body[0];
        model.addAttribute("word1", word1);
        return "result";
    }

    @GetMapping("testowyHTML")
    public String testowyHTML(){
        return "indexTest";
    }



}
