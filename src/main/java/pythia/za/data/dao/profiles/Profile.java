package pythia.za.data.dao.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Profiles")
public class Profile implements Serializable {

        @Id
        @Column(name = "profile_id", nullable = false)
        private String profile_id;

        @Column(name = "fullname")
        @JsonProperty("fullname")
        private String fullname;

        @Column(name = "cell")
        @JsonProperty("cell")
        private String cell;

        @Column(name = "email")
        @JsonProperty("email")
        private String email;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "profile_id")
        private List<AwardAchieved> awardsAchieved = new ArrayList<>();

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "profile_id")
        private List<Position> positions = new ArrayList<>();

        @Override
        public String toString() {
                return "Profile{" +
                        "profile_id='" + profile_id + '\'' +
                        ", fullname='" + fullname + '\'' +
                        ", cell='" + cell + '\'' +
                        ", email='" + email + '\'' +
                        ", awardsAchieved=" + awardsAchieved +
//                        ", positions=" + positions +
                        '}';
        }

        public Profile() {
                this.profile_id="12334";
                this.fullname = "hi BICH";
        }

        public String getProfile_id() {
                return profile_id;
        }

        public String getFullname() {
                return fullname;
        }

        public String getCell() {
                return cell;
        }

        public String getEmail() {
                return email;
        }

        public List<AwardAchieved> getAwardsAchieved() {
                return awardsAchieved;
        }

        public List<Position> getPositions() {
                return positions;
        }

        //        @Column(name = "creation_time", nullable = false)
//        @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
//        private ZonedDateTime creationTime;
//
//        @Column(name = "description", length = 500)
//        private String description;
//
//        @Column(name = "modification_time")
//        @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
//        private ZonedDateTime modificationTime;
//
//        @Column(name = "title", nullable = false, length = 100)
//        private String title;
//
//        @Version
//        private long version;

        //The constructor, builder, and other methods are omitted

}
