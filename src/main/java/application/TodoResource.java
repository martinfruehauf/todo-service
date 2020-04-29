package application;

import domain.Todo;
import domain.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
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
  private static final Logger LOG = LoggerFactory.getLogger(TodoResource.class);
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
  @Path("/{todoId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTodoByNo(@PathParam("todoId") @NotNull int todoId) {
    try {
      LOG.info("Find todo by id: {}", todoId);
      return Response.ok().entity(todoService.getTodoById(todoId)).build();
    } catch (IndexOutOfBoundsException e) {
      LOG.warn("Could not find todo by id: {}", todoId);
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }
}
