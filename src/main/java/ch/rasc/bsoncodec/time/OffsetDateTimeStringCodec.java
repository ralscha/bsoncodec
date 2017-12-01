/**
 * Copyright 2015-2017 the original author or authors.
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

import java.time.OffsetDateTime;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class OffsetDateTimeStringCodec implements Codec<OffsetDateTime> {

	@Override
	public Class<OffsetDateTime> getEncoderClass() {
		return OffsetDateTime.class;
	}

	@Override
	public void encode(BsonWriter writer, OffsetDateTime value,
			EncoderContext encoderContext) {
		writer.writeString(value.toString());
	}

	@Override
	public OffsetDateTime decode(BsonReader reader, DecoderContext decoderContext) {
		return OffsetDateTime.parse(reader.readString());
	}

}
