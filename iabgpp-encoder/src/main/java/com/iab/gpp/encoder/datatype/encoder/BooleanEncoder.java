package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class BooleanEncoder {
  public static void encode(BitStringBuilder builder, Boolean value) {
    if (value == true) {
      builder.append(true);
    } else if (value == false) {
      builder.append(false);
    } else {
      throw new EncodingException("Unencodable Boolean '" + value + "'");
    }
  }

  public static boolean decode(BitString bitString) {
    return decode(bitString, 0, bitString.length());
  }

  public static boolean decode(BitString bitString, int fromIndex, int length) {
    if (length != 1) {
      throw new DecodingException("Undecodable Boolean '" + bitString + "'");
    }
    return bitString.getValue(fromIndex);
  }
}
