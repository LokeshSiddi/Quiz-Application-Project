package com.lokesh.Quiz_Application_Project.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String category;
    private String difficulty;

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String right_answer;

}
