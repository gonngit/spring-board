package com.example.demo.question;

import com.example.demo.user.SiteUser;
import java.time.LocalDateTime;

import com.example.demo.answer.Answer;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
// Cascade is for deleting the answers when the question is deleted

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	private String subject;

	@Column(columnDefinition = "TEXT")
	private String content;

	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;

	private LocalDateTime createDate;
	
	@ManyToOne
	private SiteUser Author;


}
