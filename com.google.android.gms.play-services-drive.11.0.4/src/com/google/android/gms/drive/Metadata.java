package com.google.android.gms.drive;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.internal.zzbrc;
import com.google.android.gms.internal.zzbrp;
import com.google.android.gms.internal.zzbrx;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public abstract class Metadata implements Freezable {
   public static final int CONTENT_NOT_AVAILABLE_LOCALLY = 0;
   public static final int CONTENT_AVAILABLE_LOCALLY = 1;

   public String getAlternateLink() {
      return (String)this.zza(zzbrc.zzaPR);
   }

   public int getContentAvailability() {
      Integer var1;
      return (var1 = (Integer)this.zza(zzbrx.zzaQP)) == null ? 0 : var1.intValue();
   }

   public Date getCreatedDate() {
      return (Date)this.zza(zzbrp.zzaQI);
   }

   public Map getCustomProperties() {
      AppVisibleCustomProperties var1;
      return (var1 = (AppVisibleCustomProperties)this.zza(zzbrc.zzaPS)) == null ? Collections.emptyMap() : var1.zztl();
   }

   public String getDescription() {
      return (String)this.zza(zzbrc.zzaPT);
   }

   public DriveId getDriveId() {
      return (DriveId)this.zza(zzbrc.zzaPQ);
   }

   public String getEmbedLink() {
      return (String)this.zza(zzbrc.zzaPU);
   }

   public String getFileExtension() {
      return (String)this.zza(zzbrc.zzaPV);
   }

   public long getFileSize() {
      return ((Long)this.zza(zzbrc.zzaPW)).longValue();
   }

   public Date getLastViewedByMeDate() {
      return (Date)this.zza(zzbrp.zzaQJ);
   }

   public String getMimeType() {
      return (String)this.zza(zzbrc.zzaQn);
   }

   public Date getModifiedByMeDate() {
      return (Date)this.zza(zzbrp.zzaQL);
   }

   public Date getModifiedDate() {
      return (Date)this.zza(zzbrp.zzaQK);
   }

   public String getOriginalFilename() {
      return (String)this.zza(zzbrc.zzaQo);
   }

   public boolean isPinnable() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrx.zzaQQ)) == null ? false : var1.booleanValue();
   }

   public boolean isPinned() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQf)) == null ? false : var1.booleanValue();
   }

   public long getQuotaBytesUsed() {
      return ((Long)this.zza(zzbrc.zzaQt)).longValue();
   }

   public Date getSharedWithMeDate() {
      return (Date)this.zza(zzbrp.zzaQM);
   }

   public String getTitle() {
      return (String)this.zza(zzbrc.zzaQw);
   }

   public String getWebContentLink() {
      return (String)this.zza(zzbrc.zzaQy);
   }

   public String getWebViewLink() {
      return (String)this.zza(zzbrc.zzaQz);
   }

   public boolean isInAppFolder() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQa)) == null ? false : var1.booleanValue();
   }

   public boolean isEditable() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQc)) == null ? false : var1.booleanValue();
   }

   public boolean isFolder() {
      return "application/vnd.google-apps.folder".equals(this.getMimeType());
   }

   public boolean isRestricted() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQh)) == null ? false : var1.booleanValue();
   }

   public boolean isShared() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQi)) == null ? false : var1.booleanValue();
   }

   public boolean isStarred() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQu)) == null ? false : var1.booleanValue();
   }

   public boolean isTrashed() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQx)) == null ? false : var1.booleanValue();
   }

   public boolean isTrashable() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQl)) == null ? true : var1.booleanValue();
   }

   public boolean isExplicitlyTrashed() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQd)) == null ? false : var1.booleanValue();
   }

   public boolean isViewed() {
      Boolean var1;
      return (var1 = (Boolean)this.zza(zzbrc.zzaQm)) == null ? false : var1.booleanValue();
   }

   public abstract Object zza(MetadataField var1);
}
