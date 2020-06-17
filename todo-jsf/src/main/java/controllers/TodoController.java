package controllers;


import persist.ToDo;
import persist.ToDoRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class TodoController implements Serializable {

    @Inject
    private ToDoRepository toDoRepository;

    private ToDo todo;

    public ToDoRepository getToDoRepository() {
        return toDoRepository;
    }

    public void setToDoRepository(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDo getTodo() {
        return todo;
    }

    public void setToDo(ToDo todo) {
        this.todo = todo;
    }

    public String createTodo(){
       this.todo=new ToDo();
       return "todo.xhtml?faces-redirect=true";
    }

    public List<ToDo> getAllTodo() throws SQLException {
         return toDoRepository.findAll();
    }

    public String editTodo(ToDo todo){
        this.todo=new ToDo();
        return "todo.xhtml?faces-redirect=true";
    }

    public void deleteTodo(ToDo todo) throws SQLException {
        toDoRepository.delete(todo.getId());
    }

    public String saveTodo(ToDo todo) throws SQLException {
        if(todo.getId()==null) toDoRepository.insert(todo);
        else toDoRepository.update(todo);
        return "index.xhtml?faces-redirect=true";
    }

}
