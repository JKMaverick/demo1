package com.example.demo1.repository;

import com.example.demo1.model.db.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findAllByWord(String word);

    @Query(value = "SELECT MAX(id) FROM WORDS", nativeQuery = true)
    Long findMaxID();

    Optional<Word> findOneById(Long id);
    // ^ zadanie na sprawdzenie w SQLu
    // SELECT word, COUNT(word)
    // FROM words
    // GROUP BY word
    // HAVING COUNT(word) > 1
}
