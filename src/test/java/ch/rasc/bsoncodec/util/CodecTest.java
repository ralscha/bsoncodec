/**
 * Copyright 2015-2016 Ralph Schaer <ralphschaer@gmail.com>
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

import org.junit.Test;

import ch.rasc.bsoncodec.AbstractTest;

public class CodecTest extends AbstractTest {

	@Test
	public void testLocaleDocumentCodec() {
		writeReadCompare(Locale.ENGLISH, new LocaleDocumentCodec());
		writeReadCompare(Locale.KOREA, new LocaleDocumentCodec());
		writeReadCompare(new Locale("la", "co", "va"), new LocaleDocumentCodec());

		writeReadCompare(Locale.ENGLISH, new LocaleDocumentCodec("l", "c", "v"));
		writeReadCompare(Locale.KOREA, new LocaleDocumentCodec("l", "c", "v"));
		writeReadCompare(new Locale("la", "co", "va"),
				new LocaleDocumentCodec("l", "c", "v"));
	}

	@Test
	public void testLocaleStringCodec() {
		writeReadCompare(Locale.ENGLISH, new LocaleStringCodec());
		writeReadCompare(Locale.ITALY, new LocaleStringCodec());
		writeReadCompare(new Locale("la", "co", "va"), new LocaleStringCodec());
	}

}
