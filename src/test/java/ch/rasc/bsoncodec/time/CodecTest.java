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
package ch.rasc.bsoncodec.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.ZonedDateTime;

import org.junit.Test;

import ch.rasc.bsoncodec.AbstractTest;

public class CodecTest extends AbstractTest {

	@Test
	public void testDayOfWeekInt32Codec() {
		writeReadCompare(DayOfWeek.SUNDAY, new DayOfWeekInt32Codec());
	}

	@Test
	public void testDurationInt64Codec() {
		writeReadCompare(Duration.ofMinutes(44), new DurationInt64Codec());
	}

	@Test
	public void testDurationStringCodec() {
		writeReadCompare(Duration.ofMinutes(44), new DurationStringCodec());
	}

	@Test
	public void testInstantInt64Codec() {
		writeReadCompareInstant(Instant.now(), new InstantInt64Codec());
	}

	@Test
	public void testLocalDateDateCodec() {
		writeReadCompare(LocalDate.now(), new LocalDateDateCodec());
	}

	@Test
	public void testLocalDateStringCodec() {
		writeReadCompare(LocalDate.now(), new LocalDateStringCodec());
	}

	@Test
	public void testLocalDateTimeDateCodec() {
		writeReadCompareLocalDateTime(LocalDateTime.now(), new LocalDateTimeDateCodec());
	}

	@Test
	public void testLocalDateTimeStringCodec() {
		writeReadCompare(LocalDateTime.now(), new LocalDateTimeStringCodec());
	}

	@Test
	public void testLocalTimeStringCodec() {
		writeReadCompare(LocalTime.now(), new LocalTimeStringCodec());
	}

	@Test
	public void testMonthDayDocumentCodec() {
		writeReadCompare(MonthDay.of(Month.NOVEMBER, 19), new MonthDayDocumentCodec());
		writeReadCompare(MonthDay.of(Month.NOVEMBER, 20),
				new MonthDayDocumentCodec(true));
		writeReadCompare(MonthDay.of(Month.NOVEMBER, 21),
				new MonthDayDocumentCodec("m", "d", false));
	}

	@Test
	public void testMonthDayStringCodec() {
		writeReadCompare(MonthDay.now(), new MonthDayStringCodec());
	}

	@Test
	public void testMonthInt32Codec() {
		writeReadCompare(Month.APRIL, new MonthInt32Codec());
	}

	@Test
	public void testOffsetDateTimeStringCodec() {
		writeReadCompare(OffsetDateTime.now(), new OffsetDateTimeStringCodec());
	}

	@Test
	public void testOffsetTimeStringCodec() {
		writeReadCompare(OffsetTime.now(), new OffsetTimeStringCodec());
	}

	@Test
	public void testPeriodDocumentCodec() {
		writeReadCompare(Period.of(1, 1, 1), new PeriodDocumentCodec());
		writeReadCompare(Period.of(2, 2, 2), new PeriodDocumentCodec("y", "m", "d"));
	}

	@Test
	public void testPeriodStringCodec() {
		writeReadCompare(Period.of(0, 3, 10), new PeriodStringCodec());
	}

	@Test
	public void testYearInt32Codec() {
		writeReadCompare(Year.now(), new YearInt32Codec());
	}

	@Test
	public void testZonedDateTimeStringCodec() {
		writeReadCompare(ZonedDateTime.now(), new ZonedDateTimeStringCodec());
	}

}
