package application;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

public class TodoResourceIT {
  @Test
  public void TodoResourceReturns200WithExpectedTodosForTheMethodGetTodos() {
    RestAssured
        .when()
        .get("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/")
        .then()
        .statusCode(200)
        .body("size()", Matchers.is(3))
        .body("[0].id", Matchers.equalTo(1))
        .body("[0].name", Matchers.equalTo("Martin"))
        .body("[0].description", Matchers.equalTo("ABC"))
        .body("[0].status", Matchers.equalTo(true))
        .body("[0].dueDate", Matchers.equalTo(LocalDateTime.parse("2020-01-10T07:30:00")));
  }

  @Test
  public void TodoResourceReturns200WithExpectedTodoForTheMethodGetTodoById() {
    RestAssured
        .when()
        .get("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
        .then()
        .statusCode(200)
        .body("id", Matchers.equalTo(1))
        .body("name", Matchers.equalTo("Martin"))
        .body("description", Matchers.equalTo("ABC"))
        .body("status", Matchers.equalTo(true))
        .body("dueDate", Matchers.equalTo("2020-01-10T07:30:00"));
  }

  @Test
  public void TodoResourceReturns404ForTheMethodGetTodoById() {
    RestAssured
        .when()
        .get("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 100)
        .then()
        .statusCode(404);
  }

  @Test
  public void TodoResourceReturns201WithExpectedStringForTheMethodAddTodo() {
    RestAssured
        .given()
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new BaseTodoDTO("new name", "new description", false, LocalDateTime.MIN))
        .when()
        .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/")
        .then()
        .statusCode(201)
        .body(Matchers.equalTo("/api/todos/4"));
  }

  @Test
  public void TodoResourceReturns204ForMethodUpdateTodo() {
    RestAssured
        .given()
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new BaseTodoDTO("new name", "new description", false, LocalDateTime.MIN))
        .when()
        .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos{id}", 1)
        .then()
        .statusCode(204);
  }

}
