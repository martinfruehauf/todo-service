package application;

import domain.Todo;
import domain.TodoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
  @Inject
  private TodoService todoService;


  public TodoResource() {
  }

  public TodoResource(final TodoService todoService) {
    this.todoService = todoService;
  }

  @GET
  public Response getTodos() {
    List<Todo> list = todoService.listTodo();
    return Response.ok().entity(list).build();
  }

  @GET
  @Path("/{todoNo}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTodoById(@PathParam("todoNo") int todoNo){
    List<Todo> list = todoService.listTodo();
    return Response.ok().entity(list.get(todoNo)).build();
  }

}
