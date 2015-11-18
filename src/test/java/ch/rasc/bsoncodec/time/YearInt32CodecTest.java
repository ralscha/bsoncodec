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
import java.time.Year;

import org.bson.BsonBinaryReader;
import org.bson.BsonBinaryWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;
import org.junit.Test;

public class YearInt32CodecTest {

	@SuppressWarnings("resource")
	@Test
	public void testCodec() {
		Year now = Year.now();

		YearInt32Codec codec = new YearInt32Codec();

		BasicOutputBuffer bsonOutput = new BasicOutputBuffer();
		BsonBinaryWriter writer = new BsonBinaryWriter(bsonOutput);
		writer.writeStartDocument();
		writer.writeName("Year");
		codec.encode(writer, now, EncoderContext.builder().build());
		writer.writeEndDocument();
		writer.close();

		BsonBinaryReader reader = new BsonBinaryReader(
				ByteBuffer.wrap(bsonOutput.toByteArray()));
		reader.readStartDocument();
		assertThat(reader.readName()).isEqualTo("Year");
		Year readNow = codec.decode(reader, DecoderContext.builder().build());

		assertThat(now).isEqualTo(readNow);
	}

}
