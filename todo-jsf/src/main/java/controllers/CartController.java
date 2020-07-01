package controllers;

import services.CartService;
import services.ToDoDto;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public List<ToDoDto> getAllToDo() {
        return cartService.getAll();
    }

    public void add(ToDoDto toDoDto) {
        cartService.add(toDoDto);
    }

}
