package com.example.demo.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	
	@NotEmpty(message = "Please enter a subject.")
	@Size(max = 200)
	private String subject;

	@NotEmpty(message = "Please enter a content.")
	private String content;

}
