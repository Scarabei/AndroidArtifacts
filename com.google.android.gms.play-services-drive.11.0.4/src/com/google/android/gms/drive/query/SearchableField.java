package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.internal.zzbrc;
import com.google.android.gms.internal.zzbrp;

public class SearchableField {
   public static final SearchableMetadataField TITLE;
   public static final SearchableMetadataField MIME_TYPE;
   public static final SearchableMetadataField TRASHED;
   public static final SearchableCollectionMetadataField PARENTS;
   public static final SearchableOrderedMetadataField zzaQY;
   public static final SearchableMetadataField STARRED;
   public static final SearchableOrderedMetadataField MODIFIED_DATE;
   public static final SearchableOrderedMetadataField LAST_VIEWED_BY_ME;
   public static final SearchableMetadataField IS_PINNED;
   public static final SearchableMetadataField zzaQZ;

   static {
      TITLE = zzbrc.zzaQw;
      MIME_TYPE = zzbrc.zzaQn;
      TRASHED = zzbrc.zzaQx;
      PARENTS = zzbrc.zzaQs;
      zzaQY = zzbrp.zzaQM;
      STARRED = zzbrc.zzaQu;
      MODIFIED_DATE = zzbrp.zzaQK;
      LAST_VIEWED_BY_ME = zzbrp.zzaQJ;
      IS_PINNED = zzbrc.zzaQf;
      zzaQZ = zzbrc.zzaPS;
   }
}
