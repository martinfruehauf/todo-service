package domain;

import java.time.LocalDateTime;

public class Todo {
  private long id;

  private String name;

  private String description;

  private boolean status;

  private LocalDateTime dueDate;

  public Todo(final long id, final String name, final String description, final boolean status, final LocalDateTime dueDate) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.status = status;
    this.dueDate = dueDate;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }
}
