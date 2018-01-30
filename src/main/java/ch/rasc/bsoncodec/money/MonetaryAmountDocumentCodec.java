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
package ch.rasc.bsoncodec.money;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class MonetaryAmountDocumentCodec implements Codec<MonetaryAmount> {

	private final String currencyKey;

	private final String unscaledKey;

	private final String scaleKey;

	private final int scaleThreshold;

	public MonetaryAmountDocumentCodec() {
		this("currency", "unscaled", "scale", 18);
	}

	public MonetaryAmountDocumentCodec(String currencyKey, String unscaledKey,
			String scaleKey) {
		this(currencyKey, unscaledKey, scaleKey, 18);
	}

	public MonetaryAmountDocumentCodec(String currencyKey, String unscaledKey,
			String scaleKey, int scaleThreshold) {
		this.currencyKey = currencyKey;
		this.unscaledKey = unscaledKey;
		this.scaleKey = scaleKey;
		this.scaleThreshold = scaleThreshold;
	}

	@Override
	public Class<MonetaryAmount> getEncoderClass() {
		return MonetaryAmount.class;
	}

	@Override
	public void encode(BsonWriter writer, MonetaryAmount value,
			EncoderContext encoderContext) {
		writer.writeStartDocument();

		BigDecimal number = value.getNumber().numberValue(BigDecimal.class);

		if (number.scale() > this.scaleThreshold) {
			number = number.setScale(this.scaleThreshold, BigDecimal.ROUND_HALF_UP);
		}

		writer.writeString(this.currencyKey, value.getCurrency().getCurrencyCode());
		writer.writeInt64(this.unscaledKey, number.unscaledValue().longValue());
		writer.writeInt32(this.scaleKey, number.scale());

		writer.writeEndDocument();
	}

	@Override
	public MonetaryAmount decode(BsonReader reader, DecoderContext decoderContext) {
		reader.readStartDocument();
		CurrencyUnit currency = Monetary.getCurrency(reader.readString(this.currencyKey));
		BigDecimal number = new BigDecimal(
				BigInteger.valueOf(reader.readInt64(this.unscaledKey)),
				reader.readInt32(this.scaleKey));
		reader.readEndDocument();

		return Monetary.getDefaultAmountFactory().setNumber(number).setCurrency(currency)
				.create();
	}

}
