package app.mvc.repository;

import app.mvc.repository.basement.Base_Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class Log_Custom_Repository extends Base_Repository {

    protected Log_Custom_Repository(JPAQueryFactory query) {
        super(query);
    }

}
