package services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persist.Category;
import persist.CategoryRepository;
import persist.ToDo;
import persist.ToDoRepository;
import rest.ToDoServiceRs;
import soap.ToDoDtoWs;
import soap.ToDoServiceWs;

import javax.ejb.AsyncResult;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


@Stateless
@WebService(endpointInterface = "soap.ToDoServiceWs", serviceName = "ToDoService")
public class ToDoServiceImpl implements ToDoServiceLocal, ToDoServiceRemote, ToDoServiceWs, ToDoServiceRs {
    private static final Logger logger = LoggerFactory.getLogger(ToDoRepository.class);

    @Inject
    private ToDoRepository toDoRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    @TransactionAttribute
    public void insert(ToDoDto toDoDto) {
        Category category = categoryRepository.findById(toDoDto.getCategoryId());
        toDoRepository.insert(new ToDo(null, toDoDto.getDescription(), category));
    }


    @Override
    @TransactionAttribute
    public void update(ToDoDto toDoDto) {
        Category category = categoryRepository.findById(toDoDto.getCategoryId());
        toDoRepository.update(new ToDo(toDoDto.getId(), toDoDto.getDescription(), category));
    }

    @Override
    @TransactionAttribute
    public void delete(long id) {
        toDoRepository.delete(id);
    }

    @Override
    public ToDoDto findById(long id) {
        return toDoRepository.findToDoDtoById(id);
    }

    @Override
    public List<ToDoDto> findAll() {
        return toDoRepository.findAllToDoDto();
    }

    @Override
    public Future<ToDoDto> findByIdAsync(long id) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(toDoRepository.findToDoDtoById(id));
    }

    @Override
    public List<ToDoDtoWs> findAllWs() {
        return toDoRepository.findAll().stream().map(ToDoDtoWs::new).collect(Collectors.toList());
    }

    @Override
    public ToDoDtoWs findByIdWs(long id) {
        return new ToDoDtoWs(toDoRepository.findById(id));
    }
}

