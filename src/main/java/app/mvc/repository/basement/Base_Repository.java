package app.mvc.repository.basement;

import com.querydsl.jpa.impl.JPAQueryFactory;

public abstract class Base_Repository {

    protected final JPAQueryFactory query;

    protected Base_Repository(JPAQueryFactory query) {
        this.query = query;
    }

}
