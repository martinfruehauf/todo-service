package application;

import domain.Todo;
import domain.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TodoResourceTest {

  @InjectMocks
  private TodoResource resource;

  @Mock
  private TodoService service;

  @Test
  public void testGetTodos() {
    ArrayList<FullTodoDTO> expected = new ArrayList<>();

    expected.add(new FullTodoDTO(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30).toString()));
    expected.add(new FullTodoDTO(2, "Blubb", "Do the blubb", false, LocalDateTime.of(2020, Month.FEBRUARY, 20, 10, 00).toString()));

    ArrayList<Todo> mock = new ArrayList<>();

    mock.add(new Todo(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30)));
    mock.add(new Todo(2, "Blubb", "Do the blubb", false, LocalDateTime.of(2020, Month.FEBRUARY, 20, 10, 00)));

    Mockito.doReturn(mock)
            .when(service)
            .listTodo();
    Response response = this.resource.getTodos();

    assertEquals(expected, response.getEntity());
  }

  @Test
  public void testGetTodoById() {
    Mockito.doReturn(new Todo(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30)))
    .when(service)
    .getTodoById(1);
    FullTodoDTO expected = new FullTodoDTO(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30).toString());

    Response response = this.resource.getTodoById(1);

    assertEquals(expected, response.getEntity());
  }

  @Test
  public void testGetTodoByIdShouldFailForWrongId() {
    Mockito.doThrow(new IllegalArgumentException())
            .when(service)
            .getTodoById(100);

    Response response = this.resource.getTodoById(100);
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testAddTodo() {
    Mockito.doReturn(4L)
            .when(service)
            .addTodo(new BaseTodoDTO("name", "description", true, LocalDateTime.MIN.toString()));
    Response response = this.resource.addTodo(new BaseTodoDTO("name", "description", true, LocalDateTime.MIN.toString()));
    assertEquals(201, response.getStatus());
    assertEquals("/api/todos/4", response.getEntity());
  }

  @Test
  public void testUpdateTodo() {
    Mockito.doNothing()
            .when(service)
            .updateTodo(1, new BaseTodoDTO("new name", "new description", false, LocalDateTime.MIN.toString()));
    Response response = this.resource.updateTodo(1, new BaseTodoDTO("new name", "new description", false, LocalDateTime.MIN.toString()));
    assertEquals(204, response.getStatus());
  }

  @Test
  public void testUpdateTodoShouldFailForWrongId() {
    Mockito.doThrow(new IllegalArgumentException())
            .when(service)
            .updateTodo(100, new BaseTodoDTO("new name", "new description", false, LocalDateTime.MIN.toString()));
    Response response = this.resource.updateTodo(100, new BaseTodoDTO("new name", "new description", false, LocalDateTime.MIN.toString()));
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testDeleteTodo() {
    Mockito.doNothing()
            .when(service)
            .deleteTodo(1);
    Response response = this.resource.deleteTodo(1);
    assertEquals(204, response.getStatus());
  }

  @Test
  public void testDeleteTodoShouldFailForWrongId() {
    Mockito.doThrow(new IllegalArgumentException())
            .when(service)
            .deleteTodo(100);
    Response response = this.resource.deleteTodo(100);
    assertEquals(404, response.getStatus());
  }
}
