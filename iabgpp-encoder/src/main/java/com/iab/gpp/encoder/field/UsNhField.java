package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsNhField {

  public static final String VERSION = "Version";
  public static final String PROCESSING_NOTICE = "ProcessingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String ADDITIONAL_DATA_PROCESSING_CONSENT = "AdditionalDataProcessingConsent";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  public static final String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static final String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static final String GPC = "Gpc";
  
  //@formatter:off
  public static final List<String> USNH_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsNhField.VERSION,
      UsNhField.PROCESSING_NOTICE,
      UsNhField.SALE_OPT_OUT_NOTICE,
      UsNhField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNhField.SALE_OPT_OUT,
      UsNhField.TARGETED_ADVERTISING_OPT_OUT,
      UsNhField.SENSITIVE_DATA_PROCESSING,
      UsNhField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNhField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsNhField.MSPA_COVERED_TRANSACTION,
      UsNhField.MSPA_OPT_OUT_OPTION_MODE,
      UsNhField.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
  
  //@formatter:off
  public static final List<String> USNH_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsNhField.GPC_SEGMENT_TYPE,
      UsNhField.GPC
  });
  //@formatter:on
}
