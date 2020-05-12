package domain;

import infrastructure.stereotypes.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TodoRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public Todo findById(final long todoId) {
        return em.find(Todo.class, todoId);
    }


    public long addTodo(final Todo todo){
        em.persist(todo);
        return todo.getId();
    }


    public void deleteTodo(final long todoId) {
        Todo todo = em.find(Todo.class, todoId);
        em.remove(todo);
    }


    public void updateTodo(final Todo todo) {
        em.merge(todo);
    }

}
