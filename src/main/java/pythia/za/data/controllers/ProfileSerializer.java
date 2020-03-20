package pythia.za.data.controllers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pythia.za.data.dao.profiles.Profile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProfileSerializer extends StdSerializer<Profile> {
    public ProfileSerializer() {
        this(null);
    }

    public ProfileSerializer(Class<Profile> t) {
        super(t);
    }

    @Override
    public void serialize(Profile profile, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", profile.getProfile_id());
        jsonGenerator.writeObjectField("personalInfo", personalInfoMap(profile));
        jsonGenerator.writeObjectField("awardsAchieved", profile.getAwardsAchieved());
        jsonGenerator.writeObjectField("positions", profile.getPositions());
        jsonGenerator.writeEndObject();
    }

    private Map<String, String> personalInfoMap(Profile p) {
        return new HashMap<String, String>() {
            {
                put("fullname", p.getFullname());
                put("cell", p.getCell());
                put("email", p.getEmail());
            }
        };
    }

}