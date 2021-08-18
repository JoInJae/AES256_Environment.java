package app.data.entity.part.log;

import app.data.entity.basement.Entity_Log;
import app.data.entity.embeded.Count;
import app.data.entity.embeded.Score;
import app.data.entity.part.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity@Table(name = "Game_Log")
@Getter@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "idx", column = @Column(name = "log_idx",  columnDefinition = "BIGINT", nullable = false)),
})
public class Log extends Entity_Log {

    @Column(name = "game_name", columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @Column(name = "game_rawdata", columnDefinition = "LONGTEXT")
    private String rawdata;

    @Column(name = "game_level", columnDefinition = "LONG", nullable = false)
    private Long level;

    @AttributeOverrides({
            @AttributeOverride(name = "score_1", column = @Column(name = "game_score_acquired", columnDefinition = "FLOAT", nullable = false)),
            @AttributeOverride(name = "score_2", column = @Column(name = "game_score_total", columnDefinition = "FLOAT", nullable = false)),
    })
    @Embedded
    private Score score;

    @AttributeOverrides({
            @AttributeOverride(name = "count_1", column = @Column(name = "game_count_correct", columnDefinition = "LONG", nullable = false)),
            @AttributeOverride(name = "count_2", column = @Column(name = "game_count_total", columnDefinition = "LONG", nullable = false)),
    })
    @Embedded
    private Count count;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", referencedColumnName = "user_idx")
    private User user;

    @Builder
    public Log(String name, String rawdata, Long level, Long count_correct, Long count_total, Float score_acquired, Float score_total, User user) {
        this.name = name;
        this.rawdata = rawdata;
        this.level = level;
        this.score = new Score(score_acquired, score_total);
        this.count = new Count(count_correct, count_total);
        this.user = user;
    }


}
