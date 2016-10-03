package ch.rasc.bsoncodec.math;

import java.math.BigDecimal;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.Decimal128;

public class BigDecimalDecimal128Codec implements Codec<BigDecimal> {

	@Override
	public Class<BigDecimal> getEncoderClass() {
		return BigDecimal.class;
	}

	@Override
	public void encode(BsonWriter writer, BigDecimal value,
			EncoderContext encoderContext) {
		writer.writeDecimal128(new Decimal128(value));
	}

	@Override
	public BigDecimal decode(BsonReader reader, DecoderContext decoderContext) {
		return reader.readDecimal128().bigDecimalValue();
	}

}
