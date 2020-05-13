package application;

import infrastructure.adapters.LocalDateTimeAdapter;

import javax.json.bind.annotation.JsonbTypeAdapter;
import java.time.LocalDateTime;
import java.util.Objects;

public class BaseTodoDTO {

  private String name;

  private String description;

  private boolean status;

  @JsonbTypeAdapter(LocalDateTimeAdapter.class)
  private LocalDateTime dueDate;

  public BaseTodoDTO() {
  }

  public BaseTodoDTO(final String name, final String description, final boolean status, final LocalDateTime dueDate) {
    this.name = name;
    this.description = description;
    this.status = status;
    this.dueDate = dueDate;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(final boolean status) {
    this.status = status;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(final LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  @Override
  public String toString() {
    return "BaseTodoDTO{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", status=" + status + ", dueDate=" + dueDate
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    BaseTodoDTO that = (BaseTodoDTO)o;
    return status == that.status && Objects.equals(name, that.name) && Objects.equals(description, that.description)
        && Objects.equals(dueDate, that.dueDate);
  }
}
