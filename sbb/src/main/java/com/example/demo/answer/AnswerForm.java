package com.example.demo.answer;

import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
	@NotEmpty(message = "Please enter a content.")
	private String content;

}
