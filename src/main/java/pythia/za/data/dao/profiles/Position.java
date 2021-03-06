package pythia.za.data.dao.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Positions")
public class Position {
    @Id
    @Column(name = "position_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty("position_id")
    private String position_id;

    @Column(name = "profile_id")
//    @JsonProperty("profile_id")
    private String profile_id;

    @Column(name = "club_position")
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

    public String getPosition_id() {
        return position_id;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public String getClubPosition() {
        return clubPosition;
    }

    public String getClub() {
        return club;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public LocalDateTime getDateEnded() {
        return dateEnded;
    }

    public Position() {
        this("1234", "-1", "NOT_A_POSITION","NOT_A_CLUB", null, null);
    }

    public Position(String profile_id, String position_id, String clubPosition, String club, LocalDateTime dateStarted, LocalDateTime dateEnded) {
        this.position_id = position_id;
        this.profile_id = profile_id;
        this.clubPosition = clubPosition;
        this.club = club;
        this.dateStarted = dateStarted;
        this.dateEnded = dateEnded;
    }
}
