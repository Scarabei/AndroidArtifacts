package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.internal.zzbrc;
import com.google.android.gms.internal.zzbrp;

public class SortableField {
   public static final SortableMetadataField TITLE;
   public static final SortableMetadataField CREATED_DATE;
   public static final SortableMetadataField MODIFIED_DATE;
   public static final SortableMetadataField MODIFIED_BY_ME_DATE;
   public static final SortableMetadataField LAST_VIEWED_BY_ME;
   public static final SortableMetadataField SHARED_WITH_ME_DATE;
   public static final SortableMetadataField QUOTA_USED;
   private static SortableMetadataField zzaRc;

   static {
      TITLE = zzbrc.zzaQw;
      CREATED_DATE = zzbrp.zzaQI;
      MODIFIED_DATE = zzbrp.zzaQK;
      MODIFIED_BY_ME_DATE = zzbrp.zzaQL;
      LAST_VIEWED_BY_ME = zzbrp.zzaQJ;
      SHARED_WITH_ME_DATE = zzbrp.zzaQM;
      QUOTA_USED = zzbrc.zzaQt;
      zzaRc = zzbrp.zzaQN;
   }
}
