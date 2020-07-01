package controllers;


import persist.Category;
import persist.CategoryRepository;
import persist.ToDo;
import persist.ToDoRepository;
import services.ToDoDto;
import services.ToDoServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class TodoController implements Serializable {

    @EJB
    private ToDoServiceLocal toDoService;

    @EJB
    private CategoryRepository categoryRepository;

    private List<ToDoDto> toDoDtos;

    private ToDoDto toDoDto;

    public void preloadTodos(ComponentSystemEvent componentSystemEvent) {
        this.toDoDtos = toDoService.findAll();
    }

    public ToDoDto getToDoDto() {
        return toDoDto;
    }

    public void setToDo(ToDoDto toDoDto) {
        this.toDoDto = toDoDto;
    }

    public String createToDo() {
        this.toDoDto = new ToDoDto();
        return "/todo.xhtml?faces-redirect=true";
    }

    public List<ToDoDto> getAllTodos() {
        return toDoDtos;
    }

    public String editTodo(ToDoDto toDoDto) {
        this.toDoDto = toDoDto;
        return "/todo.xhtml?faces-redirect=true";
    }

    public void deleteTodo(ToDoDto toDoDto) {
        toDoService.delete(toDoDto.getId());
    }

    public String saveTodo() {
        if (toDoDto.getId() == null) {
            toDoService.insert(toDoDto);
        } else {
            toDoService.update(toDoDto);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

