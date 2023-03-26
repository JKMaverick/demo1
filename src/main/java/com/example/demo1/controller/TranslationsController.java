package com.example.demo1.controller;

import com.example.demo1.model.CombinedWord;
import com.example.demo1.model.db.SearchedWord;
import com.example.demo1.model.json.Word;
import com.example.demo1.repository.WordRepository;
import com.example.demo1.service.SearchedWordService;
import com.example.demo1.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("translations")
public class TranslationsController {

    private String externalApiURL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    @Autowired
    private WordService wordService;

    @Autowired
    private SearchedWordService searchedWordService;

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

    @GetMapping("saveWord")
    public ResponseEntity<com.example.demo1.model.db.Word> saveWord(@RequestParam(value = "word") String word) {
        com.example.demo1.model.db.Word dbWord = new com.example.demo1.model.db.Word();
        dbWord.setWord(word);
        dbWord.setPronunciation("abc");
        wordService.saveWord(dbWord);
        return ResponseEntity.ok(dbWord);
    }

    @GetMapping("search2")
    public ModelAndView search(@RequestParam(value = "word") String word) {
        /*
        1 dog_id 10
        2 cat_id 1
        3 turtle_id 1
         */
        List<com.example.demo1.model.db.Word> dbWord = wordService.search(word);
        Word[] wordBody = null;
        try {
            ResponseEntity<Word[]> responseEntity = translationsV2(word);
            wordBody = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Blad przy pobieraniu z api");
        }
        CombinedWord combinedWord = new CombinedWord(wordBody, dbWord);

        ModelAndView mav = new ModelAndView("finalResult"); // "result" -> inny, nowy szablon
        mav.addObject("combinedWord", combinedWord);
        return mav;
    }

    /*
    TODO przerobienie search aby zwracal szablon
    ResponseEntity<CombinedWord> -> ModelAndView analogicznie jak wyzej
     */
    @GetMapping("search")
    public ResponseEntity<CombinedWord> search_old(@RequestParam(value = "word") String word){
        List<com.example.demo1.model.db.Word> dbWord = wordService.search(word);
        Word[] wordBody = null;
        try {
            ResponseEntity<Word[]> responseEntity = translationsV2(word);
            wordBody = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Blad przy pobieraniu z api");
        }

        CombinedWord combinedWord = new CombinedWord(wordBody, dbWord);
        System.out.println(combinedWord);
        if(dbWord == null && wordBody == null){
            // ResponseEntity.status(HttpStatus.NOT_FOUND).body(combinedWord);
            ResponseEntity<CombinedWord> response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(combinedWord); //new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return response;
        } else {
            return ResponseEntity.ok(combinedWord);
        }

    }


}
