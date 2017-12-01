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
package ch.rasc.bsoncodec.math;

import java.math.BigInteger;
import java.util.Arrays;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class BigIntegerStringCodec implements Codec<BigInteger> {

	private final static char[] SIGNS = { '-', '+', '+' };

	/**
	 * If > 0 left align and zero pad the String. zeroPadding specifies the total number
	 * characters of the resulting String: sign + digits
	 */
	private final Integer zeroPadding;

	public BigIntegerStringCodec() {
		this(null);
	}

	public BigIntegerStringCodec(Integer zeroPadding) {
		this.zeroPadding = zeroPadding;
	}

	@Override
	public Class<BigInteger> getEncoderClass() {
		return BigInteger.class;
	}

	@Override
	public void encode(BsonWriter writer, BigInteger value,
			EncoderContext encoderContext) {
		if (this.zeroPadding == null) {
			writer.writeString(value.toString());
		}
		else {
			writer.writeString(formatBigInteger(value));
		}
	}

	@Override
	public BigInteger decode(BsonReader reader, DecoderContext decoderContext) {
		return new BigInteger(reader.readString());
	}

	private String formatBigInteger(BigInteger bd) {
		char[] result = new char[this.zeroPadding];
		Arrays.fill(result, '0');
		result[0] = SIGNS[bd.signum() + 1];

		String s = bd.toString();
		if (s.startsWith("-")) {
			s = s.substring(1);
		}

		char[] source = s.toCharArray();
		System.arraycopy(source, 0, result, result.length - source.length, source.length);

		return new String(result);
	}
}
