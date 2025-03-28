package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.iab.gpp.encoder.field.UsTnField;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UsTnCoreSegment;
import com.iab.gpp.encoder.segment.UsTnGpcSegment;

public class UsTn extends AbstractLazilyEncodableSection {

  public static final int ID = 22;
  public static final int VERSION = 1;
  public static final String NAME = "ustn";

  public UsTn() {
    super();
  }

  public UsTn(CharSequence encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsTn.ID;
  }

  @Override
  public String getName() {
    return UsTn.NAME;
  }

  @Override
  public int getVersion() {
    return UsTn.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    return Arrays.asList(new UsTnCoreSegment(), new UsTnGpcSegment());
  }

  @Override
  protected List<EncodableSegment> decodeSection(CharSequence encodedString) {
    if(encodedString != null && !encodedString.isEmpty()) {
      List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString,'.');
  
      if(encodedSegments.size() > 0) {
        segments.get(0).decode(encodedSegments.get(0));
      }
      
      if(encodedSegments.size() > 1) {
        segments.get(1).setFieldValue(UsTnField.GPC_SEGMENT_INCLUDED, true);
        segments.get(1).decode(encodedSegments.get(1));
      } else {
        segments.get(1).setFieldValue(UsTnField.GPC_SEGMENT_INCLUDED, false);
      }
    }

    return segments;
  }

  @Override
  protected CharSequence encodeSection(List<EncodableSegment> segments) {
    List<CharSequence> encodedSegments = new ArrayList<>(segments.size());
    
    if(!segments.isEmpty()) {
      encodedSegments.add(segments.get(0).encodeCharSequence());
      if(segments.size() >= 2 && segments.get(1).getFieldValue(UsTnField.GPC_SEGMENT_INCLUDED).equals(true)) {
        encodedSegments.add(segments.get(1).encodeCharSequence());
      }
    }
    
    return SlicedCharSequence.join('.',  encodedSegments);
  }


  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsTnField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsTnField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsTnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsTnField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsTnField.TARGETED_ADVERTISING_OPT_OUT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsTnField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsTnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsTnField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }
  
  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsTnField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsTnField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsTnField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsTnField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsTnField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsTnField.GPC);
  }
}
