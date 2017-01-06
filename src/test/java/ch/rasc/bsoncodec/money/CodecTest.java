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
package ch.rasc.bsoncodec.money;

import java.math.BigDecimal;

import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.junit.Test;

import ch.rasc.bsoncodec.AbstractTest;

public class CodecTest extends AbstractTest {

	@Test
	public void testCurrencyUnitStringCodec() {
		writeReadCompare(Monetary.getCurrency("CHF"), new CurrencyUnitStringCodec());
	}

	@Test
	public void testMonetaryAmountDocumentCodec() {
		writeReadCompare(Money.of(100.45, "EUR"), new MonetaryAmountDocumentCodec());
		writeReadCompare(Money.of(100.99, "USD"),
				new MonetaryAmountDocumentCodec("c", "u", "s"));
	}

	@Test
	public void testMonetaryAmountDocument2Codec() {
		writeReadCompare(Money.of(100.45, "EUR"), new MonetaryAmountDocument2Codec());
		writeReadCompare(Money.of(100.99, "USD"),
				new MonetaryAmountDocument2Codec("c", "n"));
		writeReadCompare(Money.of(new BigDecimal("101.01"), "USD"),
				new MonetaryAmountDocument2Codec(22));
		writeReadCompare(Money.of(new BigDecimal("101.02"), "EUR"),
				new MonetaryAmountDocument2Codec("cu", "nu", 30));
	}
}
