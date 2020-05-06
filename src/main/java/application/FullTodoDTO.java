package application;

import domain.Todo;

import java.time.LocalDateTime;
import java.util.Objects;

public class FullTodoDTO {
    private long id;

    private String name;

    private String description;

    private boolean status;

    private LocalDateTime dueDate;

    public FullTodoDTO(){
    }

    public FullTodoDTO(Todo todo) {
        this.id = todo.getId();
        this.name = todo.getName();
        this.description = todo.getDescription();
        this.status = todo.isStatus();
        this.dueDate = todo.getDueDate();
    }

    public FullTodoDTO(final long id, final String name, final String description, final boolean status, final LocalDateTime dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
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
        return "FullTodoDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dueDate=" + dueDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullTodoDTO that = (FullTodoDTO) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dueDate, that.dueDate);
    }
}
