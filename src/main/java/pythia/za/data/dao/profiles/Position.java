package pythia.za.data.dao.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Positions")
public class Position {
    @Id
    @Column(name = "award_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty("award_id")
    private String award_id;

    @Column(name = "profile_id")
    @JsonProperty("profile_id")
    private String profile_id;

    @Column(name = "clubPosition")
    @JsonProperty("clubPosition")
    private String clubPosition;

    @Column(name = "club")
    @JsonProperty("club")
    private String club;

    @Column(name = "date_started")
    @JsonProperty("dateStarted")
    private LocalDateTime dateStarted;

    @Column(name = "date_ended")
    @JsonProperty("dateEnded")
    private LocalDateTime dateEnded;

//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "profile_id")
//    private Profile profile;

}
