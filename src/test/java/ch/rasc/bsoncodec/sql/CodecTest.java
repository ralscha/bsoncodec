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
package ch.rasc.bsoncodec.sql;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Test;

import ch.rasc.bsoncodec.AbstractTest;

public class CodecTest extends AbstractTest {

	@Test
	public void testTimestampDateCodec() {
		writeReadCompare(Timestamp.from(Instant.now()), new TimestampDateCodec());
	}

	@Test
	public void testDateDateCodec() {
		writeReadCompare(new Date(Instant.now().toEpochMilli()), new DateDateCodec());
	}

}
