package app.service.basement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Service_Abstract<Repository> {

    protected final Repository repository;

    @PersistenceContext
    protected EntityManager em;

    public Service_Abstract(Repository repository) {
        this.repository = repository;
    }
}
