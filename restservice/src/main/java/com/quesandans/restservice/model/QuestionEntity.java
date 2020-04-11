package com.quesandans.restservice.model;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="Questions_Table")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="question")
    private String question;

    @Column(name="answer")
    private String answer;
}
