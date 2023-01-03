package com.example.demo1.repository;

import com.example.demo1.model.db.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<Word> findOneByWord(String word);
}
