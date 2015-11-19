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
package ch.rasc.bsoncodec.util;

import java.util.Locale;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class LocaleDocumentCodec implements Codec<Locale> {

	private final String languageKey;

	private final String countryKey;

	private final String variantKey;

	public LocaleDocumentCodec() {
		this("language", "country", "variant");
	}

	public LocaleDocumentCodec(String languageKey, String countryKey, String variantKey) {
		this.languageKey = languageKey;
		this.countryKey = countryKey;
		this.variantKey = variantKey;
	}

	@Override
	public Class<Locale> getEncoderClass() {
		return Locale.class;
	}

	@Override
	public void encode(BsonWriter writer, Locale value, EncoderContext encoderContext) {
		writer.writeStartDocument();
		writer.writeString(this.languageKey, value.getLanguage());
		if (!value.getCountry().isEmpty()) {
			writer.writeString(this.countryKey, value.getCountry());

			if (!value.getVariant().isEmpty()) {
				writer.writeString(this.variantKey, value.getVariant());
			}
		}

		writer.writeEndDocument();
	}

	@Override
	public Locale decode(BsonReader reader, DecoderContext decoderContext) {
		reader.readStartDocument();

		String language = reader.readString(this.languageKey);

		String country = "";
		String variant = "";
		if (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
			reader.readName();
			country = reader.readString();

			if (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
				reader.readName();
				variant = reader.readString();
			}
		}

		reader.readEndDocument();
		return new Locale(language, country, variant);
	}

}
