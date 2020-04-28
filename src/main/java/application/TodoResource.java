package application;

import domain.Name;
import domain.Todo;
import domain.TodoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
  @Inject
  TodoService todoService;

  @GET
  @Path("/names")
  public Response getNames() {
    List<Name> list = todoService.listNames();
    return Response.ok().entity(list).build();
  }

  @GET
  public Response getTodos() {
    List<Todo> list = todoService.listTodo();
    return Response.ok().entity(list).build();
  }

}
