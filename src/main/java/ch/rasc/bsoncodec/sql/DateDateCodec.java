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
