package com.iab.gpp.encoder.bitstring;

import java.util.AbstractList;
import java.util.BitSet;

import com.iab.gpp.encoder.error.DecodingException;

public final class BitString extends AbstractList<Boolean> {
  public static final char TRUE = '1';
  public static final char FALSE = '0';
  public static final String TRUE_STRING = new String(new char[] {TRUE});
  public static final String FALSE_STRING = new String(new char[] {FALSE});

  private final BitSet bitSet;
  private final int from;
  private final int to;

  BitString(BitSet bitSet, int from, int to) {
    this.bitSet = bitSet;
    this.from = from;
    this.to = to;
  }

  public static final BitString empty(int size) {
    return new BitString(new BitSet(size), 0, size);
  }

  public static final BitString of(String str) {
    int length = str.length();
    BitStringBuilder builder = new BitStringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = str.charAt(i);
      if (c == TRUE) {
        builder.append(true);
      } else if (c == FALSE) {
        builder.append(false);
      } else {
        throw new DecodingException("Invalid bit string");
      }
    }
    return builder.build();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(length());
    for (int i = from; i < to; i++) {
      sb.append(bitSet.get(i) ? TRUE : FALSE);
    }
    return sb.toString();
  }

  /**
   * This is the fast getter without boxing
   * @param i index
   * @return the value at that index
   */
  public boolean getValue(int i) {
    return bitSet.get(from + i);
  }

  @Override
  public Boolean get(int i) {
    return getValue(i);
  }

  @Override
  public Boolean set(int index, Boolean element) {
    Boolean old = get(index);
    bitSet.set(from + index, element);
    return old;
  }

  public int length() {
    return to - from;
  }

  @Override
  public int size() {
    return length();
  }

  public BitString substring(int i) {
    return substring(i, length());
  }

  public BitString substring(int newFrom, int newTo) {
    int length = length();
    if (newFrom > newTo || newFrom < 0 || newFrom > length || newTo > length) {
      throw new IllegalArgumentException("Invalid substring");
    }
    int oldFrom = this.from;
    return new BitString(bitSet, oldFrom + newFrom, oldFrom + newTo);
  }

  public int indexOf(String string) {
    return indexOf(string, 0);
  }

  public int indexOf(String string, int startIndex) {
    int stringLength = string.length();
    for (int i = startIndex, to = this.to; i < to; i++) {
      int match = 0;
      for (int j = 0; j < stringLength; j++) {
        if ((string.charAt(j) == TRUE) == bitSet.get(from + i + j)) {
          match++;
        }
      }
      if (match == stringLength) {
        return i;
      }
    }
    return -1;
  }

  public boolean isEmpty() {
    return length() == 0;
  }

  public BitString expandTo(int target) {
    int needed = target - length();
    if (needed == 0) {
      return this;
    }
    if (needed < 0) {
      return substring(0, target);
    }
    BitStringBuilder sb = new BitStringBuilder(target);
    sb.append(this);
    for (int i = 0; i < needed; i++) {
      sb.append(false);
    }
    return sb.build();
  }

}