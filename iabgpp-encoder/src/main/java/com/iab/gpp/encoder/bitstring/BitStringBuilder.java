package com.iab.gpp.encoder.bitstring;

import java.util.BitSet;

public final class BitStringBuilder {
  private final BitSet bitSet;
  private int length;

  public BitStringBuilder(int initialCapacity) {
    this.bitSet = new BitSet(initialCapacity);
  }

  public BitStringBuilder() {
    this.bitSet = new BitSet();
  }

  public BitString build() {
    return new BitString(bitSet, 0, length);
  }

  public void append(boolean value) {
    int idx = length++;
    if (value) {
      bitSet.set(idx);
    }
  }

  public void append(BitString other) {
    int length = other.length();
    for (int i = 0; i < length; i++) {
      append(other.getValue(i));
    }
  }
}