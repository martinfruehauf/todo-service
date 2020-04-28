package application;

import domain.Name;
import domain.Todo;
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
    this.todoResource = new TodoResource();
  }

  @Test
  public void testGetName() {
    ArrayList<Name> want = new ArrayList<>();

    want.add(new Name("Max"));
    want.add(new Name("Christian"));
    want.add(new Name("Martin"));

    Response names = this.todoResource.getNames();

    assertEquals(want, names.getEntity());
  }

  @Test
  public void testGetTodo() {
    ArrayList<Todo> want = new ArrayList<>();

    want.add(new Todo(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30)));
    want.add(new Todo(2, "Blubb", "Do the blubb", false, LocalDateTime.of(2020, Month.FEBRUARY, 20, 10, 00)));

    Response todos = this.todoResource.getTodos();

    assertEquals(want, todos.getEntity());
  }


}
