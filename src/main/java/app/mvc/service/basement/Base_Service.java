package app.mvc.service.basement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Base_Service<Repository> {

    protected final Repository repository;

    @PersistenceContext
    protected EntityManager em;

    public Base_Service(Repository repository) {
        this.repository = repository;
    }
}
