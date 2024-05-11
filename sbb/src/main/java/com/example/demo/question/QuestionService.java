package com.example.demo.question;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import com.example.demo.DataNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public List<Question> getList() {
		return questionRepository.findAll();
	}
	
	public Question getDetail(Integer id) {
		Optional<Question> question = questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("Question not found");
		}
	}
	
	public void create(String subject, String content) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		questionRepository.save(question);
	}

}
