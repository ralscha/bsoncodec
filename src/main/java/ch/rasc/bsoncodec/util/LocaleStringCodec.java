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
package ch.rasc.bsoncodec.util;

import java.util.Locale;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class LocaleStringCodec implements Codec<Locale> {

	@Override
	public Class<Locale> getEncoderClass() {
		return Locale.class;
	}

	@Override
	public void encode(BsonWriter writer, Locale value, EncoderContext encoderContext) {
		writer.writeString(value.toString());
	}

	@Override
	public Locale decode(BsonReader reader, DecoderContext decoderContext) {
		String localeString = reader.readString();

		if (!localeString.isEmpty()) {
			int index = localeString.indexOf("_");
			int index2 = localeString.indexOf("_", index + 1);
			Locale resultLocale;
			if (index == -1) {
				resultLocale = new Locale(localeString);
			}
			else if (index2 == -1) {
				resultLocale = new Locale(localeString.substring(0, index),
						localeString.substring(index + 1));
			}
			else {
				resultLocale = new Locale(localeString.substring(0, index),
						localeString.substring(index + 1, index2),
						localeString.substring(index2 + 1));

			}
			return resultLocale;
		}

		return null;

	}

}
