package com.in28minutes.springboot.todowebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class ToDoController {
	
	public ToDoController(ToDoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	private ToDoService todoService;

//	@RequestMapping("list-todos")
	@GetMapping("list-todos")
	public String listAllToDos(ModelMap model) {
		String username = getLoggedInUsername();
		List<ToDo> todos = todoService.findByUsername(username);
		model.addAttribute("todos", todos);
		return "alltodos";
	}

	private String getLoggedInUsername() {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
//	@RequestMapping(value="todo", method = RequestMethod.GET)
	@GetMapping("todo")
	public String showNewToDoPage(ModelMap model) {
		String username = getLoggedInUsername();
		ToDo todo = new ToDo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}
	
//	@RequestMapping(value="todo", method = RequestMethod.POST)
	@PostMapping("todo")
	public String addNewToDo(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUsername();
		todoService.addToDo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	
//	@RequestMapping("delete-todo")
	@GetMapping("delete-todo")
	public String deleteToDo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@GetMapping("update-todo")
	public String showUpdateToDoPage(@RequestParam int id, ModelMap model) {
		ToDo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@PostMapping("update-todo")
	public String updateToDo(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
    	String username = getLoggedInUsername();
    	todo.setUsername(username);
		todoService.updateToDo(todo);
		return "redirect:list-todos";
	}
}
