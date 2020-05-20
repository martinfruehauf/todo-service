package application;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoResourceIT {
  @Test
  @Order(1)
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
  @Order(2)
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
  @Order(3)
  void GetTodoByIdReturns404() {
    RestAssured
        .when()
        .get("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 100)
        .then()
        .statusCode(404);
  }

  @Test
  @Order(4)
  void GetTodoByIdReturns400ForNegativeId() {
    RestAssured
        .when()
        .get("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", -1)
        .then()
        .statusCode(400)
        .body(Matchers.equalTo("[{\"errorCode\":\"NEGATIVE_TODO_ID\",\"message\":\"todoId must be greater than or equal to 0\"}]"));
  }



  @Test
  @Order(5)
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
  @Order(6)
  void UpdateTodoReturns404ForNonExistingId() {
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
  @Order(7)
  void UpdateTodoReturns404ForNegativeId() {
    RestAssured
            .given()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO("new name 2", "new description 2", false, LocalDateTime.MIN.toString()))
            .when()
            .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", -100)
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"NEGATIVE_TODO_ID\",\"message\":\"todoId must be greater than or equal to 0\"}]"));
  }

  @Test
  @Order(8)
  void UpdateTodoReturns400NoBaseTodo() {
    RestAssured.given()
        .contentType(MediaType.APPLICATION_JSON)
        .when()
        .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
        .then()
        .statusCode(400)
        .body(Matchers.equalTo("[{\"errorCode\":\"BASETODO_NULL\",\"message\":\"baseTodo must not be null\"}]"));
  }

  @Test
  @Order(9)
  void UpdateTodoReturns400ForNameIsNull() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO(null, "new description", false, LocalDateTime.MIN.toString()))
            .when()
            .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"TITLE_NULL\",\"message\":\"title must not be null\"}]"));
  }

  @Test
  @Order(10)
  void UpdateTodoReturns400ForNameIsTooShort() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO("", "new description", false, LocalDateTime.MIN.toString()))
            .when()
            .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"TITLE_SIZE\",\"message\":\"title size must be between 1 and 30\"}]"));
  }

  @Test
  @Order(11)
  void UpdateTodoReturns400ForDueDateIsNull() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO("new name", "new description", false, null))
            .when()
            .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"DUEDATE_NULL\",\"message\":\"dueDate must not be null\"}]"));
  }

  @Test
  @Order(12)
  void UpdateTodoReturns400ForDescriptionIsTooLong() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO("new name", "new descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew description", false, LocalDateTime.MIN.toString()))
            .when()
            .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"DESCRIPTION_SIZE\",\"message\":\"description size must be between 0 and 500\"}]"));
  }

  @Test
  @Order(13)
  void UpdateTodoReturns400ForMultipleErrors() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO("", "new descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew description", false, null))
            .when()
            .put("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
            .then()
            .statusCode(400)
            .body(Matchers.containsString("{\"errorCode\":\"TITLE_SIZE\",\"message\":\"title size must be between 1 and 30\"}"))
            .body(Matchers.containsString("{\"errorCode\":\"DESCRIPTION_SIZE\",\"message\":\"description size must be between 0 and 500\"}"))
            .body(Matchers.containsString("{\"errorCode\":\"DUEDATE_NULL\",\"message\":\"dueDate must not be null\"}"));
  }

  @Test
  @Order(14)
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
  @Order(15)
  void AddTodoReturns400ForNoBaseTodo() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .when()
            .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos")
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"BASETODO_NULL\",\"message\":\"baseTodo must not be null\"}]"));
  }

  @Test
  @Order(16)
  void AddTodoReturns400ForNameIsNull() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO(null, "new description", false, LocalDateTime.MIN.toString()))
            .when()
            .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos")
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"TITLE_NULL\",\"message\":\"title must not be null\"}]"));
  }

  @Test
  @Order(17)
  void AddTodoReturns400ForNameIsTooShort() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO("", "new description", false, LocalDateTime.MIN.toString()))
            .when()
            .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos")
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"TITLE_SIZE\",\"message\":\"title size must be between 1 and 30\"}]"));
  }

  @Test
  @Order(18)
  void AddTodoReturns400ForDueDateIsNull() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO("new name", "new description", false, null))
            .when()
            .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos")
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"DUEDATE_NULL\",\"message\":\"dueDate must not be null\"}]"));
  }

  @Test
  @Order(19)
  void AddTodoReturns400ForDescriptionIsTooLong() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO("new name", "new descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew description", false, LocalDateTime.MIN.toString()))
            .when()
            .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos")
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"DESCRIPTION_SIZE\",\"message\":\"description size must be between 0 and 500\"}]"));
  }

  @Test
  @Order(20)
  void AddTodoReturns400ForMultipleErrors() {
    RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new BaseTodoDTO("", "new descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew descriptionnew description", false, null))
            .when()
            .post("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos")
            .then()
            .statusCode(400)
            .body(Matchers.containsString("{\"errorCode\":\"TITLE_SIZE\",\"message\":\"title size must be between 1 and 30\"}"))
            .body(Matchers.containsString("{\"errorCode\":\"DESCRIPTION_SIZE\",\"message\":\"description size must be between 0 and 500\"}"))
            .body(Matchers.containsString("{\"errorCode\":\"DUEDATE_NULL\",\"message\":\"dueDate must not be null\"}"));
  }

  @Test
  @Order(21)
  void DeleteTodoReturns204() {
    RestAssured
        .given()
        .when()
        .delete("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 1)
        .then()
        .statusCode(204);
  }

  @Test
  @Order(22)
  void DeleteTodoReturns404() {
    RestAssured
        .given()
        .when()
        .delete("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", 100)
        .then()
        .statusCode(404);
  }

  @Test
  @Order(23)
  void DeleteTodoReturns404ForNegativeId() {
    RestAssured
            .given()
            .when()
            .delete("http://localhost:8080/todo-service-1.0-SNAPSHOT/api/todos/{id}", -1)
            .then()
            .statusCode(400)
            .body(Matchers.equalTo("[{\"errorCode\":\"NEGATIVE_TODO_ID\",\"message\":\"todoId must be greater than or equal to 0\"}]"));

  }

}
