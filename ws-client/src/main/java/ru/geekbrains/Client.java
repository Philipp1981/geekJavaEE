package ru.geekbrains;

import service.ToDoService;
import service.TodoServiceWs;


public class Client {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/todo-jsf/ToDoService/ToDoServiceImpl?wsdl");
        ToDoService toDoService = new ToDoService(url);

        TodoServiceWs toDoServiceImplPort = toDoService.getToDoServiceImplPort();

        toDoServiceImplPort.findAllWs().forEach(todo -> System.out.println(todo.getDescription()));
    }
}
