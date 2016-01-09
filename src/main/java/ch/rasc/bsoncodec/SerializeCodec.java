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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bson.BsonBinary;
import org.bson.BsonInvalidOperationException;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class SerializeCodec implements Codec<Serializable> {

	private final boolean compress;

	public SerializeCodec() {
		this(false);
	}

	public SerializeCodec(boolean compress) {
		this.compress = compress;
	}

	@Override
	public Class<Serializable> getEncoderClass() {
		return Serializable.class;
	}

	@SuppressWarnings("resource")
	@Override
	public void encode(BsonWriter writer, Serializable value,
			EncoderContext encoderContext) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OutputStream os = baos;
		ObjectOutputStream oos = null;
		try {
			if (this.compress) {
				os = new GZIPOutputStream(os);
			}
			oos = new ObjectOutputStream(os);
			oos.writeObject(value);
			oos.flush();
		}
		catch (IOException e) {
			throw new BsonInvalidOperationException(e.getMessage());
		}
		finally {
			if (oos != null) {
				try {
					oos.close();
				}
				catch (IOException e) {
					// ignore this
				}
			}
			try {
				os.close();
			}
			catch (IOException e) {
				// ignore this
			}
		}

		writer.writeBinaryData(new BsonBinary(baos.toByteArray()));
	}

	@SuppressWarnings("resource")
	@Override
	public Serializable decode(BsonReader reader, DecoderContext decoderContext) {
		BsonBinary bsonBinary = reader.readBinaryData();
		ByteArrayInputStream bais = new ByteArrayInputStream(bsonBinary.getData());

		InputStream is = bais;
		try {
			if (this.compress) {
				is = new GZIPInputStream(is);
			}

			final ObjectInputStream ois = new ObjectInputStream(is);
			return (Serializable) ois.readObject();
		}
		catch (IOException | ClassNotFoundException e) {
			throw new BsonInvalidOperationException(e.getMessage());
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				// ignore this
			}
		}
	}

}
