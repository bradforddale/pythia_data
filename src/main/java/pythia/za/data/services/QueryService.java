package pythia.za.data.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pythia.za.data.dao.profiles.Profile;
import pythia.za.data.dao.profiles.ProfileRepo;
import pythia.za.data.models.QueryResult;

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

    public QueryResult create(JsonNode requestObj) {
        Profile p = new Profile(requestObj.at("/id").textValue(),
                requestObj.at("/personalInfo/fullname").textValue(),
                requestObj.at("/personalInfo/cell").textValue(),
                requestObj.at("/personalInfo/email").textValue());
        return create(p);
    }

    public QueryResult create(Profile p) {
        profileRepo.save(p);
        return new QueryResult((new SerializerService<Profile>()).serialize(p), "Successfully created a profile with id " + p.getProfile_id(), null);
    }
}
