package app.data.entity.part.log;

import app.data.entity.basement.Entity_Log;
import app.data.entity.part.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity@Table(name = "Game_Log_V2")
@Getter@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "idx", column = @Column(name = "log_idx",  columnDefinition = "BIGINT", nullable = false)),
})
public class LogV2 extends Entity_Log {

    @Column(name = "game_name", columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @Column(name = "game_rawdata", columnDefinition = "LONGTEXT")
    private String rawdata;

    @Column(name = "game_time", columnDefinition = "LONG", nullable = false)
    private Long time;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", referencedColumnName = "user_idx")
    private User user;

    @Builder
    public LogV2(String name, String rawdata, Long time, User user) {
        this.name = name;
        this.rawdata = rawdata;
        this.time = time;
        this.user = user;
    }

}
