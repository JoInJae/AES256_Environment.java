package app.repository;

import app.data.request.UserDTO;
import app.repository.basement.Base_Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class Log_Custom_Repository extends Base_Repository {

    protected Log_Custom_Repository(JPAQueryFactory query) {
        super(query);
    }

}
