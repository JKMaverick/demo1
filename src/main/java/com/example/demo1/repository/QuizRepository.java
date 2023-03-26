package com.example.demo1.repository;

import com.example.demo1.model.db.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<QuizModel, Long> {
    Optional<QuizModel> findOneById(Long id);
}
