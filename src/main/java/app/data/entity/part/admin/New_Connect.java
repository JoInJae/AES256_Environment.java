package app.data.entity.part.admin;

import javax.persistence.*;
import java.time.LocalDate;

@NamedStoredProcedureQuery(
        procedureName="XR_TEST_MAIN_PAGE", name = "XR_TEST_MAIN_PAGE")
@Entity@Table(name="NewAndConnect")
public class New_Connect {

    @Id
    @Column(name="nac_date")
    private LocalDate nac_date;

    @Column(name="nac_connection")
    private Integer nac_connection;

    @Column(name="nac_new")
    private Integer nac_new;

}
