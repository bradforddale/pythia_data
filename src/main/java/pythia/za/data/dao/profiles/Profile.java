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
                this("1234", "NON_ENTITY", "0", "0");
        }

        public Profile(String profile_id, String fullname, String cell, String email) {
                this.profile_id = profile_id;
                this.fullname = fullname;
                this.cell = cell;
                this.email = email;
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


        public void setFullname(String fullname) {
                this.fullname = fullname;
        }

        public void setCell(String cell) {
                this.cell = cell;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public void setAwardsAchieved(List<AwardAchieved> awardsAchieved) {
                this.awardsAchieved = awardsAchieved;
        }

        public void setPositions(List<Position> positions) {
                this.positions = positions;
        }
}
