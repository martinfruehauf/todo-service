package application;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

class TodoResourceIT {
  @Test
  void GetTodosReturns200WithExpectedTodos() {
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
        .body("[0].dueDate", Matchers.equalTo("2020-01-10T07:30"));
  }

  @Test
  void GetTodoByIdReturns200WithExpectedTodo() {
    RestAssured
        .when()
        .get("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
        .then()
        .statusCode(200)
        .body("id", Matchers.equalTo(1))
        .body("name", Matchers.equalTo("Martin"))
        .body("description", Matchers.equalTo("ABC"))
        .body("status", Matchers.equalTo(true))
        .body("dueDate", Matchers.equalTo("2020-01-10T07:30"));
  }

  @Test
  void GetTodoByIdReturns404() {
    RestAssured
        .when()
        .get("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 100)
        .then()
        .statusCode(404);
  }

  @Test
  void AddTodoReturns201WithExpectedString() {
    RestAssured.given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new BaseTodoDTO("new name", "new description", false, LocalDateTime.MIN.toString()))
        .when()
        .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos")
        .then()
        .contentType(MediaType.TEXT_PLAIN)
        .statusCode(201)
        .body(Matchers.equalTo("/api/todos/4"));
  }

  @Test
  void AddTodoReturns400() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .when()
            .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos")
            .then()
            .statusCode(400);
  }

  @Test
  void UpdateTodoReturns204() {
    RestAssured
        .given()
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new BaseTodoDTO("new name", "new description", false, LocalDateTime.MIN.toString()))
        .when()
        .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
        .then()
        .statusCode(204);
  }

  @Test
  void UpdateTodoReturns404() {
    RestAssured
        .given()
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new BaseTodoDTO("new name 2", "new description 2", false, LocalDateTime.MIN.toString()))
        .when()
        .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 100)
        .then()
        .statusCode(404);
  }

  @Test
  void UpdateTodoReturns400() {
    RestAssured.given()
        .contentType(MediaType.APPLICATION_JSON)
        .when()
        .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos")
        .then()
        .statusCode(400);
  }

  @Test
  void DeleteTodoReturns204() {
    RestAssured
        .given()
        .when()
        .delete("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
        .then()
        .statusCode(204);
  }

  @Test
  void DeleteTodoReturns404() {
    RestAssured
        .given()
        .when()
        .delete("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 100)
        .then()
        .statusCode(404);
  }

}
