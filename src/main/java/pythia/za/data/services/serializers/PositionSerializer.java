package pythia.za.data.services.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pythia.za.data.dao.profiles.Position;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
        jsonGenerator.writeStringField("dateStarted", DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(position.getDateStarted()));
        jsonGenerator.writeStringField("dateEnded", DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(position.getDateStarted()));
        jsonGenerator.writeEndObject();
    }
}