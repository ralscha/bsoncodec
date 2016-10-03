package ch.rasc.bsoncodec.lang;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.Decimal128;

public class StringDecimal128Codec implements Codec<String> {

	@Override
	public Class<String> getEncoderClass() {
		return String.class;
	}

	@Override
	public void encode(BsonWriter writer, String value, EncoderContext encoderContext) {
		writer.writeDecimal128(Decimal128.parse(value));
	}

	@Override
	public String decode(BsonReader reader, DecoderContext decoderContext) {
		return reader.readDecimal128().toString();
	}

}
