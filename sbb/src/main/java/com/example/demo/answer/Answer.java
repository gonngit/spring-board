package com.example.demo.answer;

import com.example.demo.user.SiteUser;
import java.time.LocalDateTime;
import java.util.Set;
import com.example.demo.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	private String content;

	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	
	@ManyToOne
	private Question question;
	
	@ManyToOne
	private SiteUser Author;
	
	@ManyToMany
	Set<SiteUser> voter;

}
