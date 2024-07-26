package com.example.demo.answer;

import java.time.LocalDateTime;
import com.example.demo.question.Question;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.demo.user.SiteUser;
import java.util.Optional;
import com.example.demo.DataNotFoundException;




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
	
	public Answer getAnswer(Integer id) {
		Optional<Answer> answer = this.answerRepository.findById(id);
		if (answer.isPresent()) {
			return answer.get();
		} else {
			throw new DataNotFoundException("Answer not found");
		}
	}
	
	public void modify(Answer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}
	
	public void delete(Answer answer) {
		this.answerRepository.delete(answer);
	}
	
	public void vote(SiteUser user, Answer answer) {
		answer.getVoter().add(user);
		this.answerRepository.save(answer);
	}
	
}
