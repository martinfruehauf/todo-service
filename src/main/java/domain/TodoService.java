package domain;

import application.BaseTodoDTO;
import infrastructure.stereotypes.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
  @Inject
  private TodoRepository todoRepository;

  private List<Todo> todoList;

  private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);

  public TodoService() {

  }

  @PostConstruct
  public void init(){
    LOG.info("TodoService initalisiert");
    todoList = new ArrayList<>();
    todoList.add(new Todo(1, "Bla", "Do the bla", true, LocalDateTime.of(2020, Month.JANUARY, 10, 7, 30)));
    todoList.add(new Todo(2, "Blubb", "Do the blubb", false, LocalDateTime.of(2020, Month.FEBRUARY, 20, 10, 00)));

  }

  public List<Todo> listTodo() {
    return todoList;
  }

  public Todo getTodoById(long todoId) {
    Todo todo = todoRepository.findById(todoId);
    if (todo == null) {
      throw new IllegalArgumentException("Could not find todo with id: " + todoId);
    }
    return todo;
  }

  public long addTodo(BaseTodoDTO baseTodoDTO) {
    Todo todo = new Todo(baseTodoDTO);
    return todoRepository.addTodo(todo);
  }

  public void updateTodo(int todoId, BaseTodoDTO baseTodoDTO) {
    Todo todo = getTodoById(todoId);
    todo.setName(baseTodoDTO.getName());
    todo.setDescription(baseTodoDTO.getDescription());
    todo.setStatus(baseTodoDTO.isStatus());
    todo.setDueDate(baseTodoDTO.getDueDate());
  }

  public void deleteTodo(int todoId) {
    todoRepository.deleteTodo(todoId);
  }
}
