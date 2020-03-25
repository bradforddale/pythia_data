package pythia.za.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pythia.za.data.dao.profiles.ProfileRepo;
import pythia.za.data.models.QueryRequest;
import pythia.za.data.models.QueryResult;
import pythia.za.data.services.QueryService;

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
            switch (query.getOperation()) {
                case "getAll": return new ResponseEntity(queryService.getAll(), HttpStatus.OK);
                case "get": return new ResponseEntity(queryService.get((String)query.getRequestObj()), HttpStatus.OK);
                default: return new ResponseEntity(new QueryResult(null, "Request operation is invalid ", null), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(new QueryResult(null, "Request must include an operation", null), HttpStatus.BAD_REQUEST);
        }
    }


}
