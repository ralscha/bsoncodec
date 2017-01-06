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
package ch.rasc.bsoncodec.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class BigDecimalStringCodec implements Codec<BigDecimal> {

	private final static char[] SIGNS = { '-', '+', '+' };

	/**
	 * If > 0 left align and zero pad the String. zeroPadding specifies the total number
	 * characters of the resulting String: sign + digits + decimal point
	 */
	private final Integer zeroPadding;

	/**
	 * If not null, the BigDecimal value will be store as unscaled value (without decimal
	 * point). During decoding the value of {@link #scale} is used for recreating the
	 * BigDecimal.
	 */
	private final Integer scale;

	public BigDecimalStringCodec() {
		this(null, null);
	}

	public BigDecimalStringCodec(Integer zeroPadding) {
		this(zeroPadding, null);
	}

	public BigDecimalStringCodec(Integer zeroPadding, Integer scale) {
		this.zeroPadding = zeroPadding;
		this.scale = scale;
	}

	@Override
	public Class<BigDecimal> getEncoderClass() {
		return BigDecimal.class;
	}

	@Override
	public void encode(BsonWriter writer, BigDecimal value,
			EncoderContext encoderContext) {
		if (this.zeroPadding == null) {
			writer.writeString(value.toString());
		}
		else {
			writer.writeString(formatBigDecimal(value));
		}
	}

	@Override
	public BigDecimal decode(BsonReader reader, DecoderContext decoderContext) {
		if (this.scale == null) {
			return new BigDecimal(reader.readString());
		}
		return new BigDecimal(new BigInteger(reader.readString()), this.scale);
	}

	private String formatBigDecimal(BigDecimal bd) {
		char[] result = new char[this.zeroPadding];
		Arrays.fill(result, '0');
		result[0] = SIGNS[bd.signum() + 1];

		String s;
		if (this.scale == null) {
			s = bd.toString();
		}
		else {
			s = bd.unscaledValue().toString();
		}

		if (s.startsWith("-")) {
			s = s.substring(1);
		}

		char[] source = s.toCharArray();
		System.arraycopy(source, 0, result, result.length - source.length, source.length);

		return new String(result);
	}

}
