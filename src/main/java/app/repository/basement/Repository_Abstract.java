package app.repository.basement;

import com.querydsl.jpa.impl.JPAQueryFactory;

public abstract class Repository_Abstract {

    protected final JPAQueryFactory query;

    protected Repository_Abstract(JPAQueryFactory query) {
        this.query = query;
    }

}
