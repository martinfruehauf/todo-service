package domain;

import application.BaseTodoDTO;
import infrastructure.stereotypes.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class TodoRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public Todo findById(final long todoId) {
        return em.find(Todo.class, todoId);
    }

    @Transactional
    public long addTodo(final Todo todo){
        em.persist(todo);
        return todo.getId();
    }

    @Transactional
    public void deleteTodo(final long todoId) {
        Todo todo = em.find(Todo.class, todoId);
        em.remove(todo);
    }

    @Transactional
    public void updateTodo(final long todoId, final BaseTodoDTO baseTodoDTO) {
        Todo todo = em.find(Todo.class, todoId);
        todo.setName(baseTodoDTO.getName());
        todo.setDescription(baseTodoDTO.getDescription());
        todo.setStatus(baseTodoDTO.isStatus());
        todo.setDueDate(baseTodoDTO.getDueDate());
    }

}
