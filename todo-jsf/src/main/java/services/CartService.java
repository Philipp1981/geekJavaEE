package services;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class CartService implements Serializable {

    private List<ToDoDto> todos = new ArrayList<>();

    public List<ToDoDto> getAll() {
        return todos;
    }

    public void add(ToDoDto todoDto) {
        todos.add(todoDto);
    }

}
