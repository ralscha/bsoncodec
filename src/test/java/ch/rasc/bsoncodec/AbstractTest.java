package ch.rasc.bsoncodec;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;

import org.bson.BsonBinaryReader;
import org.bson.BsonBinaryWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;

public class AbstractTest {

	@SuppressWarnings("resource")
	protected <T> void writeReadCompare(T now, Codec<T> codec) {
		BasicOutputBuffer bsonOutput = new BasicOutputBuffer();
		BsonBinaryWriter writer = new BsonBinaryWriter(bsonOutput);
		writer.writeStartDocument();
		writer.writeName("name");
		codec.encode(writer, now, EncoderContext.builder().build());
		writer.writeEndDocument();
		writer.close();

		BsonBinaryReader reader = new BsonBinaryReader(
				ByteBuffer.wrap(bsonOutput.toByteArray()));
		reader.readStartDocument();
		assertThat(reader.readName()).isEqualTo("name");
		T readNow = codec.decode(reader, DecoderContext.builder().build());

		assertThat(now).isEqualTo(readNow);
	}

}
