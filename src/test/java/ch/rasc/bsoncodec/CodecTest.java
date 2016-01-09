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
package ch.rasc.bsoncodec;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

public class CodecTest extends AbstractTest {

	@Test
	public void testSerializeCodec() {
		writeReadCompare(new BigDecimal("1108.291223"), new SerializeCodec());
		writeReadCompare(new BigDecimal("1108.291223"), new SerializeCodec(true));

		int[] array = new int[1000];
		Arrays.fill(array, 1);
		writeReadCompare(array, new SerializeCodec());
		writeReadCompare(array, new SerializeCodec(true));
	}

}
