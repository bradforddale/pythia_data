package pythia.za.data.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pythia.za.data.models.QueryRequest;
import pythia.za.data.models.QueryResult;
import pythia.za.data.services.QueryService;

@RestController
public class QueryController {

    @Autowired
    QueryService queryService;

    @RequestMapping(value = "/profiles/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @CrossOrigin
    public ResponseEntity profilesQuery(@RequestBody QueryRequest query) {
        try {
            if (query.getOperation() != null) {
                switch (query.getOperation()) {
                    case "getAll": return new ResponseEntity(queryService.getAll(), HttpStatus.OK);
                    case "get": return new ResponseEntity(queryService.get((String)query.getRequestObj()), HttpStatus.OK);
                    case "create": return new ResponseEntity(queryService.create(getJson(query.getRequestObj())), HttpStatus.OK);
                    default: return new ResponseEntity(new QueryResult(null, "Request operation is invalid ", null), HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity(new QueryResult(null, "Request must include an operation", null), HttpStatus.BAD_REQUEST);
            }
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity(new QueryResult(null, "requestObj could not be parsed properly", null), HttpStatus.BAD_REQUEST);
        } finally {
            System.out.println("Done");
        }
    }

    private JsonNode getJson(Object obj) throws JsonProcessingException {
        return (new ObjectMapper()).valueToTree(obj);
    }


}
