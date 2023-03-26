package com.example.demo1.repository;

import com.example.demo1.model.db.SearchedWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SearchedWordRepository extends JpaRepository<SearchedWord, Long> {

    Optional<SearchedWord> findOneByWordId(Long wordId);

    @Query(value = "Select * FROM SEARCHED_WORD ORDER BY counter DESC LIMIT 10", nativeQuery = true)
    List<SearchedWord> wordsToQuiz();

}
