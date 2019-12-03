![Build Status](https://github.com/ralscha/bsoncodec/workflows/test/badge.svg)

Version 3.7 of the MongoDB Java Driver now supports Instant, LocalDate and LocalDateTime. See more information on the [What's New](http://mongodb.github.io/mongo-java-driver/3.7/whats-new/) page.


## Collection of org.bson.codecs.Codec implementations

Codec | Java | BSON
------| ---- | ----
ch.rasc.bsoncodec.SerializeCodec | java.io.Serializable | BinaryData
ch.rasc.bsoncodec.lang.ClassStringCodec | java.lang.Class | String
ch.rasc.bsoncodec.lang.StringDecimal128Codec | java.lang.String | Decimal128
ch.rasc.bsoncodec.math.BigDecimalDecimal128Codec | java.math.BigDecimal | Decimal128  
ch.rasc.bsoncodec.math.BigDecimalDocumentCodec | java.math.BigDecimal | Document ```{"unscaled": ..., "scale": ...}```   
ch.rasc.bsoncodec.math.BigDecimalStringCodec | java.math.BigDecimal | String
ch.rasc.bsoncodec.math.BigIntegerStringCodec | java.math.BigInteger | String
ch.rasc.bsoncodec.money.CurrencyUnitStringCodec | javax.money.CurrencyUnit | String
ch.rasc.bsoncodec.money.MonetaryAmountDocument2Codec | javax.money.MonetaryAmount | Document ```{"currency": ..., "number": ...}```   
ch.rasc.bsoncodec.money.MonetaryAmountDocumentCodec | javax.money.MonetaryAmount | Document ```{"currency": ..., "unscaled": ..., "scale": ...}```   
ch.rasc.bsoncodec.net.URIStringCodec | java.net.URI | String
ch.rasc.bsoncodec.net.URLStringCodec | java.net.URL | String
ch.rasc.bsoncodec.sql.DateDateCodec | java.sql.Date | DateTime
ch.rasc.bsoncodec.sql.TimestampDateCodec | java.sql.Timestamp | DateTime
ch.rasc.bsoncodec.time.DayOfWeekInt32Codec | java.time.DayOfWeek | Int32
ch.rasc.bsoncodec.time.DurationInt64Codec | java.time.Duration | Int64
ch.rasc.bsoncodec.time.DurationStringCodec | java.time.Duration | String
ch.rasc.bsoncodec.time.InstantInt64Codec | java.time.Instant | Int64
ch.rasc.bsoncodec.time.LocalDateDateCodec | java.time.LocalDate | DateTime
ch.rasc.bsoncodec.time.LocalDateStringCodec | java.time.LocalDate | String
ch.rasc.bsoncodec.time.LocalDateTimeDateCodec | java.time.LocalDateTime | DateTime
ch.rasc.bsoncodec.time.LocalDateTimeStringCodec | java.time.LocalDateTime | String
ch.rasc.bsoncodec.time.LocalTimeStringCodec | java.time.LocalTime | String
ch.rasc.bsoncodec.time.MonthDayDocumentCodec | java.time.MonthDay | Document ```{"month": ..., "dayOfMonth": ...}```    
ch.rasc.bsoncodec.time.MonthDayStringCodec | java.time.MonthDay | String
ch.rasc.bsoncodec.time.MonthInt32Codec | java.time.Month | Int32
ch.rasc.bsoncodec.time.OffsetDateTimeStringCodec | java.time.OffsetDateTime | String
ch.rasc.bsoncodec.time.OffsetTimeStringCodec | java.time.OffsetTime | String
ch.rasc.bsoncodec.time.PeriodDocumentCodec | java.time.Period | Document ```{"years": ..., "months": ..., "days": ...}```    
ch.rasc.bsoncodec.time.PeriodStringCodec | java.time.Period | String
ch.rasc.bsoncodec.time.YearInt32Codec | java.time.Year | Int32
ch.rasc.bsoncodec.time.ZonedDateTimeStringCodec | java.time.ZonedDateTime | String
ch.rasc.bsoncodec.util.LocaleDocumentCodec | java.util.Locale | Document ```{"language": ..., "country": ..., "variant": ...}```    
ch.rasc.bsoncodec.util.LocaleStringCodec | java.util.Locale | String

## Maven

The library is available from the Maven Central Repository    
```
    <dependency>
        <groupId>ch.rasc</groupId>
        <artifactId>bsoncodec</artifactId>
        <version>1.0.1</version>
    </dependency>
```


## Changelog

### 1.0.1 - December 2, 2016
  - Add StringDecimal128Codec and BigDecimalDecimal128Codec.    
    Both codecs convert from and to the new bson datatype Decimal128    
    introduced with MongoDB 3.4

### 1.0.0 - November 22, 2015
  - Initial release


## License
Code released under [the Apache license](http://www.apache.org/licenses/).

## Other Codec libraries
   * JSR-310 codecs provider by Cezary: [bson-codecs-jsr310](https://github.com/cbartosiak/bson-codecs-jsr310)
   
## Links
   * [BSON Specification](http://bsonspec.org/)
   * [MongoDB Java Driver](https://mongodb.github.io/mongo-java-driver/)
   * [MongoDB](https://www.mongodb.org/)


