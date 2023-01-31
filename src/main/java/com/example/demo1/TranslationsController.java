package com.example.demo1;

import com.example.demo1.model.json.Word;
import com.example.demo1.repository.WordRepository;
import com.example.demo1.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("translations")
public class TranslationsController {

    private String externalApiURL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    @Autowired
    private WordService wordService;

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
    @GetMapping("search")
    public ResponseEntity<List<com.example.demo1.model.db.Word>> search(@RequestParam(value = "word") String word){
        List<com.example.demo1.model.db.Word> dbWord = wordService.search(word);
        ResponseEntity<Word[]> responseEntity = translationsV2(word);
        // TODO CombinedWord
        if(dbWord == null){
            ResponseEntity<List<com.example.demo1.model.db.Word>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return response;
        } else {
            return ResponseEntity.ok(dbWord);
        }

    }


}
