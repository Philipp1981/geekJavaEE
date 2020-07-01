package services;


import javax.ejb.Local;
import java.util.List;

@Local
public interface ToDoServiceLocal {

    void insert(ToDoDto toDoDto);

    void update(ToDoDto toDoDto);

    void delete(long id);

    ToDoDto findById(long id);

    List<ToDoDto> findAll();
}
