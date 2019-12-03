/**
 * Copyright 2015-2018 the original author or authors.
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
package ch.rasc.bsoncodec;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.assertj.core.data.TemporalUnitWithinOffset;
import org.bson.BsonBinaryReader;
import org.bson.BsonBinaryWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;

public class AbstractTest {

	@SuppressWarnings("resource")
	protected <T> void writeReadCompare(T source, Codec<T> codec) {
		BasicOutputBuffer bsonOutput = new BasicOutputBuffer();
		BsonBinaryWriter writer = new BsonBinaryWriter(bsonOutput);
		writer.writeStartDocument();
		writer.writeName("name");
		codec.encode(writer, source, EncoderContext.builder().build());
		writer.writeEndDocument();
		writer.close();

		BsonBinaryReader reader = new BsonBinaryReader(
				ByteBuffer.wrap(bsonOutput.toByteArray()));
		reader.readStartDocument();
		assertThat(reader.readName()).isEqualTo("name");
		T readNow = codec.decode(reader, DecoderContext.builder().build());

		assertThat(readNow).isEqualTo(source);
	}

	@SuppressWarnings("resource")
	protected void writeReadCompareTimestamp(Timestamp source, Codec<Timestamp> codec) {
		BasicOutputBuffer bsonOutput = new BasicOutputBuffer();
		BsonBinaryWriter writer = new BsonBinaryWriter(bsonOutput);
		writer.writeStartDocument();
		writer.writeName("name");
		codec.encode(writer, source, EncoderContext.builder().build());
		writer.writeEndDocument();
		writer.close();

		BsonBinaryReader reader = new BsonBinaryReader(
				ByteBuffer.wrap(bsonOutput.toByteArray()));
		reader.readStartDocument();
		assertThat(reader.readName()).isEqualTo("name");
		Timestamp readNow = codec.decode(reader, DecoderContext.builder().build());

		assertThat(readNow).isEqualToIgnoringMillis(source);
	}

	@SuppressWarnings("resource")
	protected void writeReadCompareInstant(Instant source, Codec<Instant> codec) {
		BasicOutputBuffer bsonOutput = new BasicOutputBuffer();
		BsonBinaryWriter writer = new BsonBinaryWriter(bsonOutput);
		writer.writeStartDocument();
		writer.writeName("name");
		codec.encode(writer, source, EncoderContext.builder().build());
		writer.writeEndDocument();
		writer.close();

		BsonBinaryReader reader = new BsonBinaryReader(
				ByteBuffer.wrap(bsonOutput.toByteArray()));
		reader.readStartDocument();
		assertThat(reader.readName()).isEqualTo("name");
		Instant readNow = codec.decode(reader, DecoderContext.builder().build());

		assertThat(readNow).isCloseTo(source,
				new TemporalUnitWithinOffset(0, ChronoUnit.MILLIS));
	}

	@SuppressWarnings("resource")
	protected void writeReadCompareLocalDateTime(LocalDateTime source, Codec<LocalDateTime> codec) {
		BasicOutputBuffer bsonOutput = new BasicOutputBuffer();
		BsonBinaryWriter writer = new BsonBinaryWriter(bsonOutput);
		writer.writeStartDocument();
		writer.writeName("name");
		codec.encode(writer, source, EncoderContext.builder().build());
		writer.writeEndDocument();
		writer.close();

		BsonBinaryReader reader = new BsonBinaryReader(
				ByteBuffer.wrap(bsonOutput.toByteArray()));
		reader.readStartDocument();
		assertThat(reader.readName()).isEqualTo("name");
		LocalDateTime readNow = codec.decode(reader, DecoderContext.builder().build());

		assertThat(readNow).isCloseTo(source,
				new TemporalUnitWithinOffset(0, ChronoUnit.MILLIS));
	}
}
