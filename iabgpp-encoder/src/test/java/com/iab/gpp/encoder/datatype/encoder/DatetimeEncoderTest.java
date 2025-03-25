package com.iab.gpp.encoder.datatype.encoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;

public class DatetimeEncoderTest {

  @Test
  public void test1() throws DecodingException {
    ZonedDateTime date1 = ZonedDateTime.now(ZoneId.of("UTC"));
    BitStringBuilder builder = new BitStringBuilder();
    DatetimeEncoder.encode(builder, date1);
    String encodedDate1 = builder.build().toString();
    ZonedDateTime date2 = DatetimeEncoder.decode(BitString.of(encodedDate1));

    Assertions.assertEquals((date1.toInstant().toEpochMilli() / 100L) * 100L, date2.toInstant().toEpochMilli());
  }
}
