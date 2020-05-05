package application;

import domain.Todo;
import domain.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoResourceTest {

  private TodoResource todoResource;

  @BeforeEach
  public void setUp() {
    this.todoResource = new TodoResource(new TodoService());
  }

  @Test
  public void testGetTodos() {
    ArrayList<Todo> want = new ArrayList<>();

    want.add(new Todo(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30)));
    want.add(new Todo(2, "Blubb", "Do the blubb", false, LocalDateTime.of(2020, Month.FEBRUARY, 20, 10, 00)));

    Response todos = this.todoResource.getTodos();

    assertEquals(want, todos.getEntity());
  }

  @Test
  public void testGetTodoById() {
    Todo expected = new Todo(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30));
    Response todo = this.todoResource.getTodoById(1);

    assertEquals(expected, todo.getEntity());
  }

  @Test
  public void testGetTodoByIdShouldFailForWrongId() {
    Response response = this.todoResource.getTodoById(100);
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testAddTodo() {
    Response response = this.todoResource.addTodo(new BaseTodoDTO("name", "description", true, LocalDateTime.now()));
    assertEquals(201, response.getStatus());
  }

  @Test
  public void testUpdateTodo() {
    Response response = this.todoResource.updateTodo(1, new BaseTodoDTO("new name", "new description", false, LocalDateTime.now()));
    assertEquals(204, response.getStatus());
  }

  @Test
  public void testUpdateTodoShouldFailForWrongId() {
    Response response = this.todoResource.updateTodo(100, new BaseTodoDTO("new name", "new description", false, LocalDateTime.now()));
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testDeleteTodo() {
    Response response = this.todoResource.deleteTodo(1);
    assertEquals(204, response.getStatus());
  }

  @Test
  public void testDeleteTodoShouldFailForWrongId() {
    Response response = this.todoResource.deleteTodo(100);
    assertEquals(404, response.getStatus());
  }
}