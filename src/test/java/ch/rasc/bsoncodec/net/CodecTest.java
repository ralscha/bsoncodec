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
package ch.rasc.bsoncodec.net;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

import ch.rasc.bsoncodec.AbstractTest;

public class CodecTest extends AbstractTest {

	@Test
	public void testURIStringCodec() throws URISyntaxException {
		writeReadCompare(new URI("mailto:me@foo.com"), new URIStringCodec());
	}

	@Test
	public void testURLStringCodec() throws MalformedURLException {
		writeReadCompare(new URL("file:///foo/bar"), new URLStringCodec());
	}
}
