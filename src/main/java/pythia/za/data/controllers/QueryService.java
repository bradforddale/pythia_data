package pythia.za.data.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pythia.za.data.dao.profiles.AwardAchieved;
import pythia.za.data.dao.profiles.Position;
import pythia.za.data.dao.profiles.Profile;
import pythia.za.data.dao.profiles.ProfileRepo;
import pythia.za.data.models.QueryResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        return new QueryResult(serializeProfiles(list), "Successful", null);
    }

    public QueryResult get(String id) {
        Optional<Profile> result = profileRepo.findById(id);
        if (result.isPresent()) {
            return new QueryResult(result.get(), "Successful", null);
        } else {
            return new QueryResult(null, "Profile with id " + id +  " not found", null);
        }
    }

    private String serializeProfiles(List<Profile> list) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Profile.class, new ProfileSerializer());
        module.addSerializer(AwardAchieved.class, new AwardAchievedSerializer());
        module.addSerializer(Position.class, new PositionSerializer());
        mapper.registerModule(module);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        mapper.setDateFormat(df);
        try {
            System.out.println(mapper.writeValueAsString(list));
            String json = mapper.writeValueAsString(list);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
