package com.example.QuizApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Response {
    @Id
    private Integer id;
    private String ans;
}
