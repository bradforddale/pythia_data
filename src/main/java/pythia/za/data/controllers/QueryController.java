package pythia.za.data.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pythia.za.data.dao.profiles.AwardAchieved;
import pythia.za.data.dao.profiles.Position;
import pythia.za.data.dao.profiles.Profile;
import pythia.za.data.dao.profiles.ProfileRepo;
import pythia.za.data.models.QueryRequest;
import pythia.za.data.models.QueryResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QueryController {

    @Autowired
    QueryService queryService;

    @Autowired
    ProfileRepo profileRepo;

    @RequestMapping(value = "/profiles/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @CrossOrigin
    public ResponseEntity profilesQuery(@RequestBody QueryRequest query) {
        if (query.getOperation() != null) {
            System.out.println(queryService.getAll());
            switch (query.getOperation()) {
                case "getAll": {
                    List<Profile> list = new ArrayList<Profile>();
                    profileRepo.findAll().forEach((Profile e) -> list.add(e));



                    return new ResponseEntity(serializeListOfProfiles(list), HttpStatus.OK);
                }
                default: return new ResponseEntity(new QueryResult(null, "Request operation is invalid ", null), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(new QueryResult(null, "Request must include an operation", null), HttpStatus.BAD_REQUEST);
        }
    }

    private String serializeListOfProfiles(List<Profile> list) {
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
