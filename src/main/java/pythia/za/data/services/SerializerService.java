package pythia.za.data.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pythia.za.data.services.serializers.AwardAchievedSerializer;
import pythia.za.data.services.serializers.PositionSerializer;
import pythia.za.data.services.serializers.ProfileSerializer;
import pythia.za.data.dao.profiles.AwardAchieved;
import pythia.za.data.dao.profiles.Position;
import pythia.za.data.dao.profiles.Profile;

import java.util.List;

public class SerializerService<T> {

    private ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Profile.class, new ProfileSerializer());
        module.addSerializer(AwardAchieved.class, new AwardAchievedSerializer());
        module.addSerializer(Position.class, new PositionSerializer());
        mapper.registerModule(module);
        return mapper;
    }

    public String serialize(T obj) {
        try {
            String json = mapper().writeValueAsString(obj);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String serializeList(List<T> list) {
        try {
            String json = mapper().writeValueAsString(list);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
