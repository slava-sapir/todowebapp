package com.in28minutes.springboot.todowebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class ToDoService {
    private static List<ToDo> todos = new ArrayList<>();
    private static int todosCount = 0;
    static {
    	todos.add(new ToDo( ++todosCount, "in28minutes", "Learn AWS", 
    			           LocalDate.now().plusYears(1), false));
    	todos.add(new ToDo( ++todosCount, "in28minutes", "Learn DevOps", 
		           LocalDate.now().plusYears(2), false));
    	todos.add(new ToDo( ++todosCount, "in28minutes", "Learn Full Stack Development", 
		           LocalDate.now().plusYears(3), false));
    }
    
    public List<ToDo> findByUsername(String username) {
    	Predicate<? super ToDo> predicate = 
    			todo -> todo.getUsername().equalsIgnoreCase(username);
    	return todos.stream().filter(predicate).toList();
    }
    
    public void addToDo
    (String username, String description, LocalDate targetDate, boolean done) {
    	ToDo todo = new ToDo( ++todosCount, username, description, targetDate, done);
    	todos.add(todo);
    }
    
    public void deleteById( int id) {
    	
    	Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
    }

	public ToDo findById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
		ToDo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateToDo(@Valid ToDo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
	}
}
