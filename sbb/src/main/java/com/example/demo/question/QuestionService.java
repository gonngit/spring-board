package com.example.demo.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import com.example.demo.user.SiteUser;
import com.example.demo.DataNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public Page<Question> getPage(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}
	
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
	
	public void create(String subject, String content, SiteUser user) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		question.setAuthor(user);
		questionRepository.save(question);
	}
	
	
	public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }
	
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	
	public void vote(Question question, SiteUser user) {
		question.getVoter().add(user);
        this.questionRepository.save(question);
	}
	
	

}
