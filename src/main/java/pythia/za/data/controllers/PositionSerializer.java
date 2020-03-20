package pythia.za.data.controllers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pythia.za.data.dao.profiles.Position;

import java.io.IOException;

public class PositionSerializer extends StdSerializer<Position> {
    public PositionSerializer() {
        this(null);
    }

    public PositionSerializer(Class<Position> t) {
        super(t);
    }

    @Override
    public void serialize(Position position, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", position.getPosition_id());
        jsonGenerator.writeStringField("clubPosition", position.getClubPosition());
        jsonGenerator.writeStringField("club", position.getClub());
        jsonGenerator.writeStringField("dateStarted", position.getDateStarted().toString());
        jsonGenerator.writeStringField("dateEnded", position.getDateEnded().toString());
        jsonGenerator.writeEndObject();
    }
}