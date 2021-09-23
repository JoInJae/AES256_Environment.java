package app.data.entity.part.log;

import app.data.entity.basement.Entity_Log;
import app.data.entity.converter.Wonju_Converter;
import app.data.entity.embeded.Score;
import app.data.entity.part.user.User;
import app.data.type.Wonju;
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

    @Column(name = "game_code", columnDefinition = "CHAR(2)", nullable = false)
    @Convert(converter = Wonju_Converter.class)
    private Wonju wonju;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", referencedColumnName = "user_idx")
    private User user;

    @Builder
    public Log(Wonju wonju, String rawdata, Long level, Float score_acquired, Float score_total, User user) {
        this.wonju = wonju;
        this.rawdata = rawdata;
        this.level = level;
        this.score = new Score(score_acquired, score_total);
        this.user = user;
    }

}
