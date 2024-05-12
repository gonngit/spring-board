package com.example.demo;

import com.example.demo.question.QuestionService;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional; // this is for findById because it returns Optional

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.question.Question;
import com.example.demo.question.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;


@SpringBootTest
class SbbApplicationTests {
	
	@Autowired
	private QuestionService questionService;

	@Test
	void testJpa() {
		
	}

}
