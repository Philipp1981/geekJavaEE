package rest;

import services.ToDoDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/todo")
public interface ToDoServiceRs {

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ToDoDto toDoDto);

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ToDoDto toDoDto);

    @DELETE
    @Path("/{id}/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") long id);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<ToDoDto> findAll();

    @GET
    @Path("/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    ToDoDto findById(@PathParam("id") long id);
}