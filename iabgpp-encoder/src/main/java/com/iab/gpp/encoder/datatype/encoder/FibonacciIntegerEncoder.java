package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerEncoder {

  public static String encode(int value) {
    List<Integer> fib = new ArrayList<Integer>();
    if (value >= 1) {
      fib.add(1);

      if (value >= 2) {
        fib.add(2);

        int i = 2;
        while (value >= fib.get(i - 1) + fib.get(i - 2)) {
          fib.add(fib.get(i - 1) + fib.get(i - 2));
          i++;
        }
      }
    }

    String bitString = BitString.TRUE_STRING;
    for (int i = fib.size() - 1; i >= 0; i--) {
      int f = fib.get(i);
      if (value >= f) {
        bitString = BitString.TRUE_STRING + bitString;
        value -= f;
      } else {
        bitString = BitString.FALSE_STRING + bitString;
      }
    }

    return bitString;
  }

  public static int decode(BitString bitString) throws DecodingException {
    return decode(bitString, 0, bitString.length());
  }

  public static int decode(BitString bitString, int fromIndex, int length) throws DecodingException {
    // must end with "11"
    int end = fromIndex + length;
    if (length < 2 || !bitString.get(end - 2) || !bitString.get(end - 1)) {
      throw new DecodingException("Undecodable FibonacciInteger '" + bitString + "'");
    }

    int value = 0;

    List<Integer> fib = new ArrayList<>(length);
    for (int i = 0; i < length - 1; i++) {
      if (i == 0) {
        fib.add(1);
      } else if (i == 1) {
        fib.add(2);
      } else {
        fib.add(fib.get(i - 1) + fib.get(i - 2));
      }
    }

    int limit = length - 1;
    for (int i = 0; i < limit; i++) {
      if (bitString.getValue(fromIndex + i)) {
        value += fib.get(i);
      }
    }
    return value;
  }
}
