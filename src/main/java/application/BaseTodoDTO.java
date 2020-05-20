package application;

import domain.TodoValidationErrorPayload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class BaseTodoDTO {

  @NotNull(payload = TodoValidationErrorPayload.TitleIsInvalid.class)
  @Size(min = 1, max = 30, payload = TodoValidationErrorPayload.TitleSize.class)
  private String name;

  @Size(max = 500, payload = TodoValidationErrorPayload.DescriptionSize.class)
  private String description;

  private boolean status;

  @NotNull(payload = TodoValidationErrorPayload.DueDateNull.class)
  private String dueDate;

  public BaseTodoDTO() {
  }

  public BaseTodoDTO(final String name, final String description, final boolean status, final String dueDate) {
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

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(final String dueDate) {
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
