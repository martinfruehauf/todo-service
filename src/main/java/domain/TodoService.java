package domain;


import infrastructure.stereotypes.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

  private List<Todo> todoList;
  private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);

  public TodoService() {
    LOG.info("TodoService injiziert");
    todoList = new ArrayList<>();
    todoList.add(new Todo(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30)));
    todoList.add(new Todo(2, "Blubb", "Do the blubb", false, LocalDateTime.of(2020, Month.FEBRUARY, 20, 10, 00)));
  }

  public List<Todo> listTodo() {
    return todoList;
  }

  public Todo getTodoById(int todoId){
    return todoList.get(todoId);
  }

}
