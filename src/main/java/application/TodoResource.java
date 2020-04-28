package application;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Todo;
import domain.TodoService;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
  @Inject
  private TodoService todoService;


  public TodoResource() {
  }

  public TodoResource(final TodoService todoService) {
    this.todoService = todoService;
    List<Todo> list = todoService.listTodo();
  }

  @GET
  public Response getTodos() {
    //List<Todo> list = todoService.listTodo();
    return Response.ok().entity(list).build();
  }

  @GET
  @Path("/{todoId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTodoById(@PathParam("todoId") long todoId){
    return Response.ok().entity(list.get(todoId)).build();
  }

}
