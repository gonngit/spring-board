package com.example.demo.answer;

import java.time.LocalDateTime;
import com.example.demo.question.Question;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.demo.user.SiteUser;


@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;

	public void create(Question question, String content, SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepository.save(answer);
	}
}
