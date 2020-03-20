package pythia.za.data.controllers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pythia.za.data.dao.profiles.AwardAchieved;

import java.io.IOException;

public class AwardAchievedSerializer extends StdSerializer<AwardAchieved> {
    public AwardAchievedSerializer() {
        this(null);
    }

    public AwardAchievedSerializer(Class<AwardAchieved> t) {
        super(t);
    }

    @Override
    public void serialize(AwardAchieved award, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", award.getAward_id());
        jsonGenerator.writeStringField("description", award.getDescription());
        jsonGenerator.writeStringField("dateAchieved", award.getDateAchieved().toString());
        jsonGenerator.writeEndObject();
    }
}