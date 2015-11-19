package ch.rasc.bsoncodec.time;

import java.time.MonthDay;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class MonthDayStringCodec implements Codec<MonthDay> {

	@Override
	public Class<MonthDay> getEncoderClass() {
		return MonthDay.class;
	}

	@Override
	public void encode(BsonWriter writer, MonthDay value, EncoderContext encoderContext) {
		writer.writeString(value.toString());
	}

	@Override
	public MonthDay decode(BsonReader reader, DecoderContext decoderContext) {
		return MonthDay.parse(reader.readString());
	}

}
