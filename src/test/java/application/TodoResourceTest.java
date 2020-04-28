package application;

import domain.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
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
}
