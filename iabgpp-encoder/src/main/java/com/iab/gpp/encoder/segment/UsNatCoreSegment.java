package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.EncodableFlexibleIntegerList;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.field.UsNatField;
import com.iab.gpp.encoder.section.UsNat;

public final class UsNatCoreSegment extends AbstractBase64Segment<UsNatField> {

  public UsNatCoreSegment() {
    super(UsNatField.USNAT_CORE_SEGMENT_FIELD_NAMES);
    initialize(UsNatField.VERSION, new EncodableFixedInteger(6, UsNat.VERSION));
    initialize(UsNatField.SHARING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.SHARING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.SHARING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.SENSITIVE_DATA_PROCESSING,
        new EncodableFlexibleIntegerList(2, this::getSensitiveDataProcessingSize)
            .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    initialize(UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFlexibleIntegerList(2, this::getKnownChildSensitiveDataConsentsSize)
        .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    initialize(UsNatField.PERSONAL_DATA_CONSENTS,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNatField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
  }
  
  private boolean isV1() {
    return get(UsNatField.VERSION).getValue().equals(1);
  }

  private int getSensitiveDataProcessingSize() {
    if (isV1()) {
      return 12;
    }
    return 16;
  }

  private int getKnownChildSensitiveDataConsentsSize() {
    if (isV1()) {
      return 2;
    }
    return 3;
  }

  @Override
  protected BitString decodeBitString(CharSequence encodedString) {
    BitString bitString = super.decodeBitString(encodedString);
    // Necessary to maintain backwards compatibility when sensitive data processing changed from a
    // length of 12 to 16 and known child sensitive data consents changed from a length of 2 to 3 in the
    // DE, IA, NE, NH, NJ, TN release
    // The heuristic is to check for version 1 strings which are too long
    if (!bitString.getValue(4) && bitString.getValue(5) && bitString.length() >= 72) {
      BitStringBuilder builder = new BitStringBuilder();
      // upgrade to version 2
      FixedIntegerEncoder.encode(builder, 2, 6);
      builder.append(bitString, 6, bitString.length());
      bitString = builder.build();
    }
    return bitString;
  }
  
}
