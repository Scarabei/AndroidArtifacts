package com.google.android.gms.drive;

import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbrc;
import com.google.android.gms.internal.zzbrp;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public final class MetadataChangeSet {
   public static final int INDEXABLE_TEXT_SIZE_LIMIT_BYTES = 131072;
   public static final int CUSTOM_PROPERTY_SIZE_LIMIT_BYTES = 124;
   public static final int MAX_PUBLIC_PROPERTIES_PER_RESOURCE = 30;
   public static final int MAX_PRIVATE_PROPERTIES_PER_RESOURCE_PER_APP = 30;
   public static final int MAX_TOTAL_PROPERTIES_PER_RESOURCE = 100;
   public static final MetadataChangeSet zzaMz = new MetadataChangeSet(MetadataBundle.zztp());
   private final MetadataBundle zzaMA;

   public MetadataChangeSet(MetadataBundle var1) {
      this.zzaMA = var1.zztq();
   }

   public final Map getCustomPropertyChangeMap() {
      AppVisibleCustomProperties var1;
      return (var1 = (AppVisibleCustomProperties)this.zzaMA.zza(zzbrc.zzaPS)) == null ? Collections.emptyMap() : var1.zztl();
   }

   public final String getDescription() {
      return (String)this.zzaMA.zza(zzbrc.zzaPT);
   }

   public final String getIndexableText() {
      return (String)this.zzaMA.zza(zzbrc.zzaPZ);
   }

   public final Date getLastViewedByMeDate() {
      return (Date)this.zzaMA.zza(zzbrp.zzaQJ);
   }

   public final String getMimeType() {
      return (String)this.zzaMA.zza(zzbrc.zzaQn);
   }

   public final String getTitle() {
      return (String)this.zzaMA.zza(zzbrc.zzaQw);
   }

   public final Boolean isPinned() {
      return (Boolean)this.zzaMA.zza(zzbrc.zzaQf);
   }

   public final Boolean isStarred() {
      return (Boolean)this.zzaMA.zza(zzbrc.zzaQu);
   }

   public final Boolean isViewed() {
      return (Boolean)this.zzaMA.zza(zzbrc.zzaQm);
   }

   public final MetadataBundle zzsW() {
      return this.zzaMA;
   }

   public final MetadataChangeSet zza(MetadataField var1, Object var2) {
      MetadataChangeSet var3;
      (var3 = new MetadataChangeSet(this.zzaMA)).zzaMA.zzc(var1, var2);
      return var3;
   }

   public static class Builder {
      private final MetadataBundle zzaMA = MetadataBundle.zztp();
      private AppVisibleCustomProperties.zza zzaMB;

      private final AppVisibleCustomProperties.zza zzsX() {
         if (this.zzaMB == null) {
            this.zzaMB = new AppVisibleCustomProperties.zza();
         }

         return this.zzaMB;
      }

      private static int zzcP(String var0) {
         return var0 == null ? 0 : var0.getBytes().length;
      }

      private static void zzi(String var0, int var1, int var2) {
         zzbo.zzb(var2 <= var1, String.format("%s must be no more than %d bytes, but is %d bytes.", var0, var1, var2));
      }

      public MetadataChangeSet.Builder setCustomProperty(CustomPropertyKey var1, String var2) {
         zzbo.zzb(var1, "key");
         zzbo.zzb(var2, "value");
         int var3 = zzcP(var1.getKey()) + zzcP(var2);
         zzi("The total size of key string and value string of a custom property", 124, var3);
         this.zzsX().zza(var1, var2);
         return this;
      }

      public MetadataChangeSet.Builder deleteCustomProperty(CustomPropertyKey var1) {
         zzbo.zzb(var1, "key");
         this.zzsX().zza(var1, (String)null);
         return this;
      }

      public MetadataChangeSet.Builder setDescription(String var1) {
         this.zzaMA.zzc(zzbrc.zzaPT, var1);
         return this;
      }

      public MetadataChangeSet.Builder setIndexableText(String var1) {
         int var2 = zzcP(var1);
         zzi("Indexable text size", 131072, var2);
         this.zzaMA.zzc(zzbrc.zzaPZ, var1);
         return this;
      }

      public MetadataChangeSet.Builder setLastViewedByMeDate(Date var1) {
         this.zzaMA.zzc(zzbrp.zzaQJ, var1);
         return this;
      }

      public MetadataChangeSet.Builder setMimeType(String var1) {
         this.zzaMA.zzc(zzbrc.zzaQn, var1);
         return this;
      }

      public MetadataChangeSet.Builder setPinned(boolean var1) {
         this.zzaMA.zzc(zzbrc.zzaQf, var1);
         return this;
      }

      public MetadataChangeSet.Builder setStarred(boolean var1) {
         this.zzaMA.zzc(zzbrc.zzaQu, var1);
         return this;
      }

      public MetadataChangeSet.Builder setTitle(String var1) {
         this.zzaMA.zzc(zzbrc.zzaQw, var1);
         return this;
      }

      public MetadataChangeSet.Builder setViewed(boolean var1) {
         this.zzaMA.zzc(zzbrc.zzaQm, var1);
         return this;
      }

      public MetadataChangeSet build() {
         if (this.zzaMB != null) {
            this.zzaMA.zzc(zzbrc.zzaPS, this.zzaMB.zztm());
         }

         return new MetadataChangeSet(this.zzaMA);
      }
   }
}
