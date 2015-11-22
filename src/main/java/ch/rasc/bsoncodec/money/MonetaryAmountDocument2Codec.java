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
package ch.rasc.bsoncodec.money;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class MonetaryAmountDocument2Codec implements Codec<MonetaryAmount> {

	private final static char[] SIGNS = { '-', '+', '+' };

	private final String currencyKey;

	private final String numberKey;

	/**
	 * If > 0 left align and zero pad the String. zeroPadding specifies the total number
	 * characters of the resulting String: sign + digits + decimal point
	 */
	private final Integer zeroPadding;

	public MonetaryAmountDocument2Codec() {
		this(null);
	}

	public MonetaryAmountDocument2Codec(Integer zeroPadding) {
		this("currency", "number", zeroPadding);
	}

	public MonetaryAmountDocument2Codec(String currencyKey, String numberKey) {
		this(currencyKey, numberKey, null);
	}

	public MonetaryAmountDocument2Codec(String currencyKey, String numberKey,
			Integer zeroPadding) {
		this.currencyKey = currencyKey;
		this.numberKey = numberKey;
		this.zeroPadding = zeroPadding;
	}

	@Override
	public Class<MonetaryAmount> getEncoderClass() {
		return MonetaryAmount.class;
	}

	@Override
	public void encode(BsonWriter writer, MonetaryAmount value,
			EncoderContext encoderContext) {
		writer.writeStartDocument();

		writer.writeString(this.currencyKey, value.getCurrency().getCurrencyCode());

		BigDecimal number = value.getNumber().numberValue(BigDecimal.class);
		if (this.zeroPadding == null) {
			writer.writeString(this.numberKey, number.toString());
		}
		else {
			writer.writeString(this.numberKey, formatBigDecimal(number));
		}

		writer.writeEndDocument();
	}

	@Override
	public MonetaryAmount decode(BsonReader reader, DecoderContext decoderContext) {
		reader.readStartDocument();
		CurrencyUnit currency = Monetary.getCurrency(reader.readString(this.currencyKey));
		BigDecimal number = new BigDecimal(reader.readString(this.numberKey));
		reader.readEndDocument();

		return Monetary.getDefaultAmountFactory().setNumber(number).setCurrency(currency)
				.create();
	}

	private String formatBigDecimal(BigDecimal bd) {
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
