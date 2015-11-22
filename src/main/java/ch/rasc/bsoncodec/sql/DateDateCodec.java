package ch.rasc.bsoncodec.sql;

import java.sql.Date;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class DateDateCodec implements Codec<Date> {

	@Override
	public Class<Date> getEncoderClass() {
		return Date.class;
	}

	@Override
	public void encode(BsonWriter writer, Date value, EncoderContext encoderContext) {
		writer.writeDateTime(value.getTime());
	}

	@Override
	public Date decode(BsonReader reader, DecoderContext decoderContext) {
		return new Date(reader.readDateTime());
	}

}
