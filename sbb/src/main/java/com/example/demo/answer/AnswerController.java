package com.example.demo.answer;

import com.example.demo.question.Question;
import com.example.demo.user.SiteUser;
import com.example.demo.user.UserService;
import com.example.demo.question.QuestionService;

import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/answer")
public class AnswerController {
	
	private final AnswerService answerService;
	private final QuestionService questionService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm form, BindingResult bindingResult, Principal principal) {
		Question question = this.questionService.getDetail(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		this.answerService.create(question, form.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s", id);
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String modifyAnswer(AnswerForm answerForm, @PathVariable("id") Integer id, BindingResult bindingResult, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No permission to modify the answer");
		}
		answerForm.setContent(answer.getContent());
		return "answer_form";
    }
    
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String modifyAnswer(@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable("id") Integer id, Principal principal) {
        
        if (bindingResult.hasErrors()) {
            return "answer_form";
        }
        Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No permission to modify the answer");
		}
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String deleteAnswer(@PathVariable("id") Integer id, Principal principal) {
		Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No permission to delete the answer");
		}
		this.answerService.delete(answer);
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String voteAnswer(Principal principal, @PathVariable("id") Integer id) {
		SiteUser user = this.userService.getUser(principal.getName());
		Answer answer = this.answerService.getAnswer(id);
		this.answerService.vote(user, answer);
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}
	
	
	

}
