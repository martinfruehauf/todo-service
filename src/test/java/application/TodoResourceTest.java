package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoResourceTest {

  private TodoResource todoResource;

  @BeforeEach
  public void setUp() {
    this.todoResource = new TodoResource();
  }

  @Test
  public void testGetName() {
    JsonArray jsonResponse = Json.createArrayBuilder()
        .add(Json.createObjectBuilder().add("name", "Max"))
        .add(Json.createObjectBuilder().add("name", "Christian"))
        .add(Json.createObjectBuilder().add("name", "Martin")).build();
    System.out.println(jsonResponse);
    Response names = this.todoResource.getNames();
    assertEquals(jsonResponse, names.getEntity());
  }
}
