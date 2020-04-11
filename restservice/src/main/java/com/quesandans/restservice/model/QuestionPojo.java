package com.quesandans.restservice.model;


import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionPojo {
    private String question;
    private String answer;
}
