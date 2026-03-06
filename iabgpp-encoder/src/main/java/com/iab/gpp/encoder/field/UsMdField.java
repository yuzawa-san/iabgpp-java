package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFibonacciIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.section.UsIn;

public enum UsMdField implements FieldKey {
  SECTION_ID(new EncodableFixedInteger<>("SectionID", 6, UsIn.ID)),
  VERSION(new EncodableFixedInteger<>("Version", 6, UsIn.VERSION)),
  SUB_SECTIONS(new EncodableFibonacciIntegerRange<>("SubSections")),

  MSPA_VERSION(new EncodableFixedInteger<>("MspaVersion", 6, 0)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, VALIDATOR_12)),
  MSPA_MODE(new EncodableFixedInteger<>("MspaMode", 2, 0, VALIDATOR_012)),
  PROCESSING_NOTICE(new EncodableFixedInteger<>("ProcessingNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, VALIDATOR_012)),
  ADDITIONAL_DATA_PROCESSING_CONSENT(
      new EncodableFixedInteger<>("AdditionalDataProcessingConsent", 2, 0, VALIDATOR_012)),

  GPC_SUBSECTION_TYPE(new EncodableFixedInteger<>("GpcSubSectionType", 2, 1)),
  GPC(new EncodableBoolean<>("Gpc", false));

  private final DataType<UsMdField, ?> type;

  UsMdField(DataType<UsMdField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsMdField, ?> getType() {
    return type;
  }

  public static final FieldNames<UsMdField> USMD_HEADER_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsMdField.SECTION_ID, UsMdField.VERSION, UsMdField.SUB_SECTIONS);

  public static final FieldNames<UsMdField> USMD_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsMdField.MSPA_VERSION,
          UsMdField.MSPA_COVERED_TRANSACTION,
          UsMdField.MSPA_MODE,
          UsMdField.PROCESSING_NOTICE,
          UsMdField.SALE_OPT_OUT_NOTICE,
          UsMdField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsMdField.SALE_OPT_OUT,
          UsMdField.TARGETED_ADVERTISING_OPT_OUT,
          UsMdField.ADDITIONAL_DATA_PROCESSING_CONSENT);

  public static final FieldNames<UsMdField> USMD_GPC_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsMdField.GPC_SUBSECTION_TYPE, UsMdField.GPC);
}
