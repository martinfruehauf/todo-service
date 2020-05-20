package application;

import domain.Todo;
import domain.TodoService;
import domain.TodoValidationErrorPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {
  private static final Logger LOG = LoggerFactory.getLogger(TodoResource.class);

  @Inject
  private TodoService todoService;

  public TodoResource() {
    LOG.info("Todo Resource created");
  }

  public TodoResource(final TodoService todoService) {
    this.todoService = todoService;
  }

  @GET
  public Response getTodos() {
    LOG.info("Get all todos");
    List<FullTodoDTO> listFullTodoDTO = new ArrayList<>();
    for (Todo todo: todoService.listTodo()) {
      listFullTodoDTO.add(new FullTodoDTO(todo));
    }
    return Response.ok().entity(listFullTodoDTO).build();
  }

  @GET
  @Path("/{todoId}")
  public Response getTodoById(@PathParam("todoId") @Min(value = 0, payload = TodoValidationErrorPayload.NegativeTodoId.class) final long todoId) {
    try {
      LOG.info("Get todo by id: {}", todoId);
      Todo todo = todoService.getTodoById(todoId);
      FullTodoDTO fullTodoDTO = new FullTodoDTO(todo);
      return Response.ok().entity(fullTodoDTO).build();
    } catch (IllegalArgumentException e) {
      LOG.warn("Could not find todo by id: {}", todoId);
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  public Response addTodo(@Valid @NotNull(payload =  TodoValidationErrorPayload.BaseTodoNull.class) final BaseTodoDTO baseTodoDTO) {
    LOG.info("Create new todo");
    long todoId = todoService.addTodo(baseTodoDTO);
    String uri = "/api/todos/" + todoId;
    return Response.status(Response.Status.CREATED).entity(uri).build();
  }

  @PUT
  @Path("/{todoId}")
  @Transactional
  public Response updateTodo(@PathParam("todoId") @Min(value = 0, payload = TodoValidationErrorPayload.NegativeTodoId.class) final long todoId, @Valid @NotNull(payload =  TodoValidationErrorPayload.BaseTodoNull.class) final BaseTodoDTO baseTodoDTO) {
    try {
      LOG.info("Update todo by id: {}", todoId);
      todoService.updateTodo(todoId, baseTodoDTO);
      return Response.status(Response.Status.NO_CONTENT).build();
    } catch (IllegalArgumentException e) {
      LOG.warn("Update todo by id: {} not possible", todoId);
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @DELETE
  @Path("/{todoId}")
  @Transactional
  public Response deleteTodo(@PathParam("todoId") @Min(value = 0, payload = TodoValidationErrorPayload.NegativeTodoId.class) final long todoId) {
    try {
      LOG.info("Delete todo by id: {}", todoId);
      todoService.deleteTodo(todoId);
      return Response.status(Response.Status.NO_CONTENT).build();
    } catch (IllegalArgumentException e) {
      LOG.warn("Delete todo by id: {} not possible", todoId);
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }
}
