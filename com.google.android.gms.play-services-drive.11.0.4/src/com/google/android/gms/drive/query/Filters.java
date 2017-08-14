package com.google.android.gms.drive.query;

import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.query.internal.zzn;
import com.google.android.gms.drive.query.internal.zzp;
import com.google.android.gms.drive.query.internal.zzr;
import com.google.android.gms.drive.query.internal.zzv;
import com.google.android.gms.drive.query.internal.zzx;
import com.google.android.gms.drive.query.internal.zzz;

public class Filters {
   public static Filter eq(SearchableMetadataField var0, Object var1) {
      return new com.google.android.gms.drive.query.internal.zzb(zzx.zzaRu, var0, var1);
   }

   public static Filter lessThan(SearchableOrderedMetadataField var0, Comparable var1) {
      return new com.google.android.gms.drive.query.internal.zzb(zzx.zzaRv, var0, var1);
   }

   public static Filter greaterThan(SearchableOrderedMetadataField var0, Comparable var1) {
      return new com.google.android.gms.drive.query.internal.zzb(zzx.zzaRx, var0, var1);
   }

   public static Filter lessThanEquals(SearchableOrderedMetadataField var0, Comparable var1) {
      return new com.google.android.gms.drive.query.internal.zzb(zzx.zzaRw, var0, var1);
   }

   public static Filter greaterThanEquals(SearchableOrderedMetadataField var0, Comparable var1) {
      return new com.google.android.gms.drive.query.internal.zzb(zzx.zzaRy, var0, var1);
   }

   public static Filter in(SearchableCollectionMetadataField var0, Object var1) {
      return new zzp(var0, var1);
   }

   public static Filter eq(CustomPropertyKey var0, String var1) {
      zzbo.zzb(var1 != null, "Custom property value may not be null.");
      return new zzn(SearchableField.zzaQZ, (new AppVisibleCustomProperties.zza()).zza(var0, var1).zztm());
   }

   public static Filter contains(SearchableMetadataField var0, String var1) {
      return new com.google.android.gms.drive.query.internal.zzb(zzx.zzaRC, var0, var1);
   }

   public static Filter and(Filter var0, Filter... var1) {
      return new zzr(zzx.zzaRz, var0, var1);
   }

   public static Filter and(Iterable var0) {
      return new zzr(zzx.zzaRz, var0);
   }

   public static Filter or(Filter var0, Filter... var1) {
      return new zzr(zzx.zzaRA, var0, var1);
   }

   public static Filter or(Iterable var0) {
      return new zzr(zzx.zzaRA, var0);
   }

   public static Filter not(Filter var0) {
      return new zzv(var0);
   }

   public static Filter sharedWithMe() {
      return new com.google.android.gms.drive.query.internal.zzd(SearchableField.zzaQY);
   }

   public static Filter openedByMe() {
      return new com.google.android.gms.drive.query.internal.zzd(SearchableField.LAST_VIEWED_BY_ME);
   }

   public static Filter ownedByMe() {
      return new zzz();
   }
}
