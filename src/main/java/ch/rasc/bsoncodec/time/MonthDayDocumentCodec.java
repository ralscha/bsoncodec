/**
 * Copyright 2015-2017 Ralph Schaer <ralphschaer@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
