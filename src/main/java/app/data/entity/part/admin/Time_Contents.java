package app.data.entity.part.admin;

import javax.persistence.*;

@Entity@Table(name="Time_By_Contents")
public class Time_Contents {

    @Id
    @Column(name="tbc_idx")
    private Long tbc_idx;

    @Column(name="tbc_hour")
    private Integer tbc_hour;

    @Column(name="tbc_content")
    private String tbc_content;

    @Column(name="tbc_runtime")
    private Integer tbc_runtime;

}
