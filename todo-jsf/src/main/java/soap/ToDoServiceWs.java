package soap;

import soap.ToDoDtoWs;

import javax.jws.WebMethod;
import java.util.List;

public interface ToDoServiceWs {

    @WebMethod
    List<ToDoDtoWs> findAllWs();

    @WebMethod
    ToDoDtoWs findByIdWs(long id);
}
