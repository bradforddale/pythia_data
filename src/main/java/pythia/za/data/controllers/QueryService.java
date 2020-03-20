package pythia.za.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pythia.za.data.dao.profiles.Profile;
import pythia.za.data.dao.profiles.ProfileRepo;
import pythia.za.data.models.QueryResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {
    @Autowired
    private ProfileRepo profileRepo;

    public QueryResult getAll() {
        List<Profile> list = new ArrayList<Profile>();
        profileRepo.findAll().forEach((Profile e) -> list.add(e));
//        profileRepo.findAll().forEach(e -> list.add(e));
//        System.out.println(list.size());
        System.out.println(list);
        return new QueryResult(list, "successful", "BOO");
    }
}
