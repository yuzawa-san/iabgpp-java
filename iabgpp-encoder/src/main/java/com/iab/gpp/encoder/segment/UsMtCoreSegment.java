package com.iab.gpp.encoder.segment;

import java.util.Arrays;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringEncoder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.UsMtField;
import com.iab.gpp.encoder.section.UsMt;

public final class UsMtCoreSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();
  private static final BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public UsMtCoreSegment() {
    super();
  }

  public UsMtCoreSegment(CharSequence encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    EncodableBitStringFields fields = new EncodableBitStringFields(UsMtField.USMT_CORE_SEGMENT_FIELD_NAMES);
    fields.put(UsMtField.VERSION, new EncodableFixedInteger(6, UsMt.VERSION));
    fields.put(UsMtField.SHARING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsMtField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsMtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsMtField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsMtField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsMtField.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0))
            .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    fields.put(UsMtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0))
        .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    fields.put(UsMtField.ADDITIONAL_DATA_PROCESSING_CONSENT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsMtField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsMtField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsMtField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields fields) {
    BitStringBuilder bitString = bitStringEncoder.encode(fields);
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields fields) {
    if (encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      bitStringEncoder.decode(bitString, fields);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UsMtCoreSegment '" + encodedString + "'", e);
    }
  }

}
