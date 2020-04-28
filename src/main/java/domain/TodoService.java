package domain;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TodoService {

  List<Todo> todoList;

  public TodoService() {
    System.out.println("TodoService injiziert");
  }

  public List<Todo> listTodo() {
    todoList = new ArrayList<>();
    todoList.add(new Todo(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30)));
    todoList.add(new Todo(2, "Blubb", "Do the blubb", false, LocalDateTime.of(2020, Month.FEBRUARY, 20, 10, 00)));
    return todoList;
  }
}
