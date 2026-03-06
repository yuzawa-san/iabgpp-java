package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.field.FieldNames;

public final class Base64Segment<E extends Enum<E> & FieldKey> extends AbstractBase64Segment<E> {

  private final boolean optional;

  public Base64Segment(FieldNames<E> fieldNames, boolean optional) {
    super(fieldNames);
    this.optional = optional;
  }

  public Base64Segment(FieldNames<E> fieldNames) {
    this(fieldNames, false);
  }

  protected AbstractBase64UrlEncoder getBase64UrlEncoder() {
    return CompressedBase64UrlEncoder.getInstance();
  }

  @Override
  public boolean shouldEncode() {
    return !optional || this.isPresent();
  }
}
