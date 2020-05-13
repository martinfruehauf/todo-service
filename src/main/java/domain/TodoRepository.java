package domain;

import infrastructure.stereotypes.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TodoRepository {
  @PersistenceContext(unitName = "default")
  private EntityManager em;

  private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);

  public List<Todo> getTodos() {
    LOG.info("Get all todos");
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Todo> cq = cb.createQuery(Todo.class);
    Root<Todo> root = cq.from(Todo.class);
    CriteriaQuery<Todo> all = cq.select(root);
    TypedQuery<Todo> allQuery = em.createQuery(all);
    return allQuery.getResultList();
  }

  public Todo findById(final long todoId) {
    LOG.info("Get todo by id: {}", todoId);
    return em.find(Todo.class, todoId);
  }

  public long addTodo(final Todo todo) {
    LOG.info("Add todo");
    em.persist(todo);
    return todo.getId();
  }

  public void deleteTodo(final long todoId) {
    LOG.info("Delete todo by id: {}", todoId);
    Todo todo = em.find(Todo.class, todoId);
    em.remove(todo);
  }

  public void updateTodo(final Todo todo) {
    LOG.info("Update todo");
    em.merge(todo);
  }

}
