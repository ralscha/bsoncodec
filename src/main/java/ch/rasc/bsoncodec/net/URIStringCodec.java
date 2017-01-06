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
package ch.rasc.bsoncodec.net;

import java.net.URI;
import java.net.URISyntaxException;

import org.bson.BsonInvalidOperationException;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class URIStringCodec implements Codec<URI> {

	@Override
	public Class<URI> getEncoderClass() {
		return URI.class;
	}

	@Override
	public void encode(BsonWriter writer, URI value, EncoderContext encoderContext) {
		writer.writeString(value.toString());
	}

	@Override
	public URI decode(BsonReader reader, DecoderContext decoderContext) {
		String uriString = reader.readString();
		try {
			return new URI(uriString);
		}
		catch (URISyntaxException e) {
			throw new BsonInvalidOperationException(
					String.format("Cannot create URI from string '%s'", uriString));

		}
	}

}
