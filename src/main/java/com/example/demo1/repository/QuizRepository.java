package com.example.demo1.repository;

import com.example.demo1.model.db.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizModel, Long> {
}
