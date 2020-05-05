package domain;

import infrastructure.stereotypes.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TodoRepository {
    @Inject
    @PersistenceContext(unitName = "default")
    private EntityManager em;
}
