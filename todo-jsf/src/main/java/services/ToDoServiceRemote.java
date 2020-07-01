package services;

import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface ToDoServiceRemote {

    List<ToDoDto> findAll();

    @Asynchronous
    Future<ToDoDto> findByIdAsync(long id);
}
