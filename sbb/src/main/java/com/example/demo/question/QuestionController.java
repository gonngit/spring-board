package com.example.demo.question;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody; // using template instead of response body
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import com.example.demo.answer.AnswerForm;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	
	@GetMapping("/list") // more general than getmapping
	//@GetMapping("/question/list")
	//@ResponseBody
	public String list(Model model) {
		//List<Question> questionList = questionRepository.findAll();
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
		return "question_list"; // file name of the template
	}
	
	@GetMapping("/detail/{id}")
	//@GetMapping("/question/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getDetail(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "question_form";
		}
		// create question (method needed from question service)
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";
	}
	
}
