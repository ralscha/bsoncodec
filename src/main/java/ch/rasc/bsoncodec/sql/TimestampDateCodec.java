package ch.rasc.bsoncodec.sql;

import java.sql.Timestamp;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class TimestampDateCodec implements Codec<Timestamp> {

	@Override
	public Class<Timestamp> getEncoderClass() {
		return Timestamp.class;
	}

	@Override
	public void encode(BsonWriter writer, Timestamp value,
			EncoderContext encoderContext) {
		writer.writeDateTime(value.getTime());
	}

	@Override
	public Timestamp decode(BsonReader reader, DecoderContext decoderContext) {
		return new Timestamp(reader.readDateTime());
	}

}
