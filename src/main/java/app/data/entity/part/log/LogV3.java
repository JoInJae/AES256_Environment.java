package app.data.entity.part.log;

import app.data.entity.basement.Entity_Log;
import app.data.entity.embeded.Count;
import app.data.entity.embeded.Score;
import app.data.entity.part.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity@Table(name = "Game_Log_V3")
@Getter@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "idx", column = @Column(name = "log_idx",  columnDefinition = "BIGINT", nullable = false)),
})
public class LogV3 extends Entity_Log {

    @Column(name = "game_name", columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @Column(name = "game_rawdata", columnDefinition = "LONGTEXT")
    private String rawdata;

    @Column(name = "game_level", columnDefinition = "LONG", nullable = false)
    private Long level;

    @Column(name = "game_type", columnDefinition = "INT(1)", nullable = false)
    private Long type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", referencedColumnName = "user_idx")
    private User user;

    @Column(name = "game_limit", columnDefinition = "LONG", nullable = false)
    private Long limit;

    @Column(name = "game_time", columnDefinition = "LONG", nullable = false)
    private Long time;

    @Builder
    public LogV3(String name, String rawdata, Long level, Long type, Long limit, Long time, User user) {
        this.name = name;
        this.rawdata = rawdata;
        this.level = level;
        this.type = type;
        this.limit = limit;
        this.time = time;
        this.user = user;
    }


}
