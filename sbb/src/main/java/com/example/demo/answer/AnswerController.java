package com.example.demo.answer;

import com.example.demo.question.Question;
import com.example.demo.question.QuestionService;

import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
@RequestMapping("/answer")
public class AnswerController {
	
	private final AnswerService answerService;
	private final QuestionService questionService;
	
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm form, BindingResult bindingResult) {
		Question question = this.questionService.getDetail(id);
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		this.answerService.create(question, form.getContent());
		return String.format("redirect:/question/detail/%s", id);
	}

}
