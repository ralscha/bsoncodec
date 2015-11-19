package ch.rasc.bsoncodec.time;

import java.time.Month;
import java.time.MonthDay;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class MonthDayDocumentCodec implements Codec<MonthDay> {

	private final String monthKey;

	private final String dayOfMonthKey;

	private final boolean monthAsString;

	public MonthDayDocumentCodec() {
		this("month", "dayOfMonth", false);
	}

	public MonthDayDocumentCodec(boolean monthAsString) {
		this("month", "dayOfMonth", monthAsString);
	}

	public MonthDayDocumentCodec(String monthKey, String dayOfMonthKey,
			boolean monthAsString) {
		this.monthKey = monthKey;
		this.dayOfMonthKey = dayOfMonthKey;
		this.monthAsString = monthAsString;
	}

	@Override
	public Class<MonthDay> getEncoderClass() {
		return MonthDay.class;
	}

	@Override
	public void encode(BsonWriter writer, MonthDay value, EncoderContext encoderContext) {
		writer.writeStartDocument();

		if (this.monthAsString) {
			writer.writeString(this.monthKey, value.getMonth().name());
		}
		else {
			writer.writeInt32(this.monthKey, value.getMonthValue());
		}

		writer.writeInt32(this.dayOfMonthKey, value.getDayOfMonth());
		writer.writeEndDocument();
	}

	@Override
	public MonthDay decode(BsonReader reader, DecoderContext decoderContext) {
		MonthDay monthDay;
		reader.readStartDocument();

		if (this.monthAsString) {
			monthDay = MonthDay.of(Month.valueOf(reader.readString(this.monthKey)),
					reader.readInt32(this.dayOfMonthKey));
		}
		else {
			monthDay = MonthDay.of(reader.readInt32(this.monthKey),
					reader.readInt32(this.dayOfMonthKey));
		}

		reader.readEndDocument();

		return monthDay;
	}

}
