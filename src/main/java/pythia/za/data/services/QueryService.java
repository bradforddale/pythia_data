package pythia.za.data.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pythia.za.data.dao.profiles.AwardAchieved;
import pythia.za.data.dao.profiles.Position;
import pythia.za.data.dao.profiles.Profile;
import pythia.za.data.dao.profiles.ProfileRepo;
import pythia.za.data.models.QueryResult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QueryService {
    @Autowired
    private ProfileRepo profileRepo;

    public QueryResult getAll() {
        List<Profile> list = new ArrayList<Profile>();
        profileRepo.findAll().forEach((Profile e) -> list.add(e));
        return new QueryResult((new SerializerService<Profile>()).serializeList(list), "Successful", null);
    }

    public QueryResult get(String id) {
        Optional<Profile> result = profileRepo.findById(id);
        if (result.isPresent()) {
            return new QueryResult((new SerializerService<Profile>()).serialize(result.get()), "Successful", null);
        } else {
            return new QueryResult(null, "Profile with id " + id +  " not found", null);
        }
    }

    public QueryResult update(JsonNode requestObj) {
        Optional<Profile> p = profileRepo.findById(requestObj.at("/id").textValue());
        if (p.isPresent()) {
            Profile newProfile = p.get();
            newProfile.setFullname(requestObj.at("/personalInfo/fullname").textValue());
            newProfile.setCell(requestObj.at("/personalInfo/cell").textValue());
            newProfile.setEmail(requestObj.at("/personalInfo/email").textValue());
            ArrayList<AwardAchieved> awardAchieveds = new ArrayList<>();
            ArrayList<Position> positions = new ArrayList<>();
            for (int i = 0; i < requestObj.at("/personalInfo/awardsAchieved").size(); i++) {
                JsonNode awardNode = requestObj.at("/personalInfo/awardsAchieved").get(i);
                awardAchieveds.add(new AwardAchieved(newProfile.getProfile_id(),
                        awardNode.at("/id").textValue(),
                        awardNode.at("/description").textValue(),
                        LocalDate.parse(awardNode.at("/dateAchieved").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay()));
            }

            newProfile.setAwardsAchieved(awardAchieveds);
            for (int j = 0 ; j < requestObj.at("/personalInfo/awardsAchieved").size(); j++) {
                JsonNode positionsJson = requestObj.at("/personalInfo/positions");
                Position po = new Position(newProfile.getProfile_id(),
                        positionsJson.at("/id").textValue(),
                        positionsJson.at("/clubPosition").textValue(),
                        positionsJson.at("/club").textValue(),
                        LocalDate.parse(positionsJson.at("/dateStarted").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay(),
                        LocalDate.parse(positionsJson.at("/dateEnded").textValue(), DateTimeFormatter.ofPattern("yyyy/MM/d")).atStartOfDay());
                positions.add(po);
            }
            newProfile.setPositions(positions);
            profileRepo.save(newProfile);
            return new QueryResult((new SerializerService<Profile>()).serialize(newProfile), "Successfully updated a profile with id " + newProfile.getProfile_id(), null);
        } else {
            return new QueryResult(null, "Profile could not be found to update", null);
        }
    }

    public QueryResult delete(String id) {
        profileRepo.deleteById(id);
        return new QueryResult(null, "Successfully deleted a profile with id " + id, null);
    }

    public QueryResult create(JsonNode requestObj) {
        Profile p = new Profile(requestObj.at("/id").textValue(),
                requestObj.at("/personalInfo/fullname").textValue(),
                requestObj.at("/personalInfo/cell").textValue(),
                requestObj.at("/personalInfo/email").textValue());
        return create(p);
    }

    private QueryResult create(Profile p) {
        profileRepo.save(p);
        return new QueryResult((new SerializerService<Profile>()).serialize(p), "Successfully created a profile with id " + p.getProfile_id(), null);
    }
}
