package app.data.entity;

import app.data.entity.basement.Entity_Log;
import app.data.request.type.Production;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity@Table(name = "LOG")
@Getter@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "idx", column = @Column(name = "log_idx",  columnDefinition = "BIGINT", nullable = false)),
})
public class Log extends Entity_Log {

    @Column(name = "name", columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @Column(name = "rawdata", columnDefinition = "LONGTEXT")
    private String rawdata;

    @Column(name = "level", columnDefinition = "LONG", nullable = false)
    private Long level;

    @AttributeOverrides({
            @AttributeOverride(name = "score_1", column = @Column(name = "acquired_score", columnDefinition = "FLOAT", nullable = false)),
            @AttributeOverride(name = "score_2", column = @Column(name = "total_score", columnDefinition = "FLOAT", nullable = false)),
    })
    @Embedded
    private Score score;

    @AttributeOverrides({
            @AttributeOverride(name = "count_1", column = @Column(name = "correct_count", columnDefinition = "LONG", nullable = false)),
            @AttributeOverride(name = "count_2", column = @Column(name = "total_count", columnDefinition = "LONG", nullable = false)),
    })
    @Embedded
    private Count count;

    @Column(name = "production", columnDefinition = "VARCHAR(10)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Production production;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", referencedColumnName = "user_idx")
    private User user;

    @Builder
    public Log(String name, String rawdata, Long level, Long count_1, Long count_2, Float score_1, Float score_2, Production production, User user) {
        this.name = name;
        this.rawdata = rawdata;
        this.level = level;
        this.score = new Score(score_1, score_2);
        this.count = new Count(count_1, count_2);
        this.production = production;
        this.user = user;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @Getter
    public static class Count{
        private Long count_1;
        private Long count_2;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @Getter
    public static class Score{
        private Float score_1;
        private Float score_2;
    }

}
