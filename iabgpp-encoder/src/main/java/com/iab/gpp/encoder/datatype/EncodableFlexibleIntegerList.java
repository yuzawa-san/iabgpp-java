package com.iab.gpp.encoder.datatype;

import java.util.List;
import java.util.function.IntSupplier;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerListEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFlexibleIntegerList extends AbstractDirtyableBitStringDataType<FixedIntegerList> {

  private int elementBitStringLength;
  private IntSupplier numElementsSupplier;

  public EncodableFlexibleIntegerList(int elementBitStringLength, IntSupplier numElementsSupplier) {
    super(true);
    this.elementBitStringLength = elementBitStringLength;
    this.numElementsSupplier = numElementsSupplier;
    super.setValue(new FixedIntegerList(numElementsSupplier.getAsInt()), false);
  }

  public void encode(BitStringBuilder builder) {
    try {
      FixedIntegerListEncoder.encode(builder, this.value, this.elementBitStringLength, this.numElementsSupplier.getAsInt());
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitStringReader reader) {
    try {
      int size = this.numElementsSupplier.getAsInt();
      if (this.value.size() != size) {
        this.value = new FixedIntegerList(size);
      }
      FixedIntegerListEncoder.decode(this.value, reader, this.elementBitStringLength);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object value) {
    List<Integer> list = (List<Integer>) value;
    int size = list.size();
    int numElements = this.numElementsSupplier.getAsInt();
    for (int i = 0; i < numElements; i++) {
      this.value.set(i, i < size ? list.get(i) : 0);
    }
    // call validator
    super.setValue(this.value);
  }
}
