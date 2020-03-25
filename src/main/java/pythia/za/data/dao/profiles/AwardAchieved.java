package pythia.za.data.dao.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Awards_Achieved")
public class AwardAchieved {
    @Column(name = "profile_id")
//    @JsonProperty("profile_id")
    private String profile_id;

    @Id
    @Column(name = "award_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty("award_id")
    private String award_id;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "date_achieved")
    @JsonProperty("dateAchieved")
    private LocalDateTime dateAchieved;

    public AwardAchieved() {
        this("1234", "-1", "NOT_AN_AWARD", null);
    }

    public AwardAchieved(String profile_id, String award_id, String description, LocalDateTime dateAchieved) {
        this.profile_id = profile_id;
        this.award_id = award_id;
        this.description = description;
        this.dateAchieved = dateAchieved;
    }

    @Override
    public String toString() {
        return "AwardAchieved{" +
                "profile_id='" + profile_id + '\'' +
                ", award_id='" + award_id + '\'' +
                ", description='" + description + '\'' +
                ", dateAchieved=" + dateAchieved +
                '}';
    }

    public String getProfile_id() {
        return profile_id;
    }

    public String getAward_id() {
        return award_id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateAchieved() {
        return dateAchieved;
    }
}
