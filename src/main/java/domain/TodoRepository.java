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
}
