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
