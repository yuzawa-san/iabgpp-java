package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.field.UsNhField;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UsNhCoreSegment;
import com.iab.gpp.encoder.segment.UsNhGpcSegment;

public class UsNh extends AbstractLazilyEncodableSection {

  public static int ID = 20;
  public static int VERSION = 1;
  public static String NAME = "usnh";

  public UsNh() {
    super();
  }

  public UsNh(String encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsNh.ID;
  }

  @Override
  public String getName() {
    return UsNh.NAME;
  }

  @Override
  public int getVersion() {
    return UsNh.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    List<EncodableSegment> segments = new ArrayList<>();
    segments.add(new UsNhCoreSegment());
    segments.add(new UsNhGpcSegment());
    return segments;
  }

  @Override
  protected List<EncodableSegment> decodeSection(String encodedString) {
    List<EncodableSegment> segments = initializeSegments();

    if(encodedString != null && !encodedString.isEmpty()) {
      String[] encodedSegments = encodedString.split("\\.");
  
      if(encodedSegments.length > 0) {
        segments.get(0).decode(encodedSegments[0]);
      }
      
      if(encodedSegments.length > 1) {
        segments.get(1).setFieldValue(UsNhField.GPC_SEGMENT_INCLUDED, true);
        segments.get(1).decode(encodedSegments[1]);
      } else {
        segments.get(1).setFieldValue(UsNhField.GPC_SEGMENT_INCLUDED, false);
      }
    }

    return segments;
  }

  @Override
  protected String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>();
    
    if(!segments.isEmpty()) {
      encodedSegments.add(segments.get(0).encode());
      if(segments.size() >= 2 && segments.get(1).getFieldValue(UsNhField.GPC_SEGMENT_INCLUDED).equals(true)) {
        encodedSegments.add(segments.get(1).encode());
      }
    }
    
    return String.join(".", encodedSegments);
  }


  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsNhField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsNhField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsNhField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsNhField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsNhField.TARGETED_ADVERTISING_OPT_OUT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsNhField.SENSITIVE_DATA_PROCESSING);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getKnownChildSensitiveDataConsents() {
    return (List<Integer>) this.getFieldValue(UsNhField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsNhField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }
  
  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsNhField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsNhField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsNhField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsNhField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsNhField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsNhField.GPC);
  }
}
