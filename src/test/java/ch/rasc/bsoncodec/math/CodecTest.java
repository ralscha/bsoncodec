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
package ch.rasc.bsoncodec.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import ch.rasc.bsoncodec.AbstractTest;

public class CodecTest extends AbstractTest {

	@Test
	public void testBigDecimalDocumentCodec() {
		writeReadCompare(new BigDecimal("100.45"), new BigDecimalDocumentCodec());
		writeReadCompare(new BigDecimal("100.46"), new BigDecimalDocumentCodec("u", "s"));
		writeReadCompare(new BigDecimal("100.111"),
				new BigDecimalDocumentCodec("u", "s", 3));
	}

	@Test
	public void testBigDecimalStringCodec() {
		writeReadCompare(new BigDecimal("99.91"), new BigDecimalStringCodec());
		writeReadCompare(new BigDecimal("99.92"), new BigDecimalStringCodec(22));
		writeReadCompare(new BigDecimal("99.93"), new BigDecimalStringCodec(30, 2));
	}

	@Test
	public void testBigIntegerStringCodec() {
		writeReadCompare(new BigInteger("11"), new BigIntegerStringCodec());
		writeReadCompare(new BigInteger("1133"), new BigIntegerStringCodec(50));
	}

	@Test
	public void testBigDecimalDecimal128Codec() {
		writeReadCompare(new BigDecimal("0"), new BigDecimalDecimal128Codec());
		writeReadCompare(new BigDecimal("1"), new BigDecimalDecimal128Codec());
		writeReadCompare(new BigDecimal("-1"), new BigDecimalDecimal128Codec());
		writeReadCompare(new BigDecimal(String.valueOf(Long.MAX_VALUE)),
				new BigDecimalDecimal128Codec());
		writeReadCompare(new BigDecimal(String.valueOf(Long.MIN_VALUE)),
				new BigDecimalDecimal128Codec());
	}
}
