package app.utility;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Component
public class BatchWork {

    @PersistenceContext
    private EntityManager em;

    @Scheduled(cron = "0 0 1 * * *")
    public void batch_main_page_info(){

        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("XR_TEST_MAIN_PAGE");

        spq.execute();// 결과 반환

    }

}
