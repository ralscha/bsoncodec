/**
 * Copyright 2015-2015 Ralph Schaer <ralphschaer@gmail.com>
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

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;

import org.bson.BsonBinaryReader;
import org.bson.BsonBinaryWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;
import org.junit.Test;

public class CodecTest {

	@Test
	public void testDayOfWeekInt32Codec() {
		writeReadCompare(DayOfWeek.SUNDAY, new DayOfWeekInt32Codec());
	}

	@Test
	public void testDayOfWeekStringCodec() {
		writeReadCompare(DayOfWeek.MONDAY, new DayOfWeekStringCodec());
	}
	
	@Test
	public void testDurationStringCodec() {
		writeReadCompare(Duration.ofMinutes(44), new DurationStringCodec());
	}	

	@Test
	public void testInstantInt64Codec() {
		writeReadCompare(Instant.now(), new InstantInt64Codec());
	}

	@Test
	public void testMonthDayDocumentCodec() {
		writeReadCompare(MonthDay.of(Month.NOVEMBER, 19), new MonthDayDocumentCodec());
		writeReadCompare(MonthDay.of(Month.NOVEMBER, 20),
				new MonthDayDocumentCodec(true));
		writeReadCompare(MonthDay.of(Month.NOVEMBER, 21),
				new MonthDayDocumentCodec("m", "d", false));
	}

	@Test
	public void testMonthDayStringCodec() {
		writeReadCompare(MonthDay.now(), new MonthDayStringCodec());
	}

	@Test
	public void testMonthInt32Codec() {
		writeReadCompare(Month.APRIL, new MonthInt32Codec());
	}

	@Test
	public void testMonthStringCodec() {
		writeReadCompare(Month.NOVEMBER, new MonthStringCodec());
	}

	@Test
	public void testYearInt32Codec() {
		writeReadCompare(Year.now(), new YearInt32Codec());
	}

	@SuppressWarnings("resource")
	private static <T> void writeReadCompare(T now, Codec<T> codec) {
		BasicOutputBuffer bsonOutput = new BasicOutputBuffer();
		BsonBinaryWriter writer = new BsonBinaryWriter(bsonOutput);
		writer.writeStartDocument();
		writer.writeName("name");
		codec.encode(writer, now, EncoderContext.builder().build());
		writer.writeEndDocument();
		writer.close();

		BsonBinaryReader reader = new BsonBinaryReader(
				ByteBuffer.wrap(bsonOutput.toByteArray()));
		reader.readStartDocument();
		assertThat(reader.readName()).isEqualTo("name");
		T readNow = codec.decode(reader, DecoderContext.builder().build());

		assertThat(now).isEqualTo(readNow);
	}

}
