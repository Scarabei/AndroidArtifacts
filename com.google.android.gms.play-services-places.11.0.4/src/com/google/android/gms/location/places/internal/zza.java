package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zza extends com.google.android.gms.common.internal.safeparcel.zza implements AutocompletePrediction {
   public static final Creator CREATOR = new zzc();
   private static final List zzbkb = Collections.emptyList();
   private String zzbkc;
   private String zzbjI;
   private List zzbjj;
   private List zzbkd;
   private int zzbke;
   private String zzbkf;
   private List zzbkg;
   private String zzbkh;
   private List zzbki;

   zza(String var1, List var2, int var3, String var4, List var5, String var6, List var7, String var8, List var9) {
      this.zzbjI = var1;
      this.zzbjj = var2;
      this.zzbke = var3;
      this.zzbkc = var4;
      this.zzbkd = var5;
      this.zzbkf = var6;
      this.zzbkg = var7;
      this.zzbkh = var8;
      this.zzbki = var9;
   }

   @Nullable
   public final String getPlaceId() {
      return this.zzbjI;
   }

   public final List getPlaceTypes() {
      return this.zzbjj;
   }

   public final CharSequence getFullText(@Nullable CharacterStyle var1) {
      return zzg.zza(this.zzbkc, this.zzbkd, var1);
   }

   public final CharSequence getPrimaryText(@Nullable CharacterStyle var1) {
      return zzg.zza(this.zzbkf, this.zzbkg, var1);
   }

   public final CharSequence getSecondaryText(@Nullable CharacterStyle var1) {
      return zzg.zza(this.zzbkh, this.zzbki, var1);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbkc, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbjI, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbjj, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbkd, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzbke);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbkf, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzbkg, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbkh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 9, this.zzbki, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("placeId", this.zzbjI).zzg("placeTypes", this.zzbjj).zzg("fullText", this.zzbkc).zzg("fullTextMatchedSubstrings", this.zzbkd).zzg("primaryText", this.zzbkf).zzg("primaryTextMatchedSubstrings", this.zzbkg).zzg("secondaryText", this.zzbkh).zzg("secondaryTextMatchedSubstrings", this.zzbki).toString();
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbjI, this.zzbjj, this.zzbke, this.zzbkc, this.zzbkd, this.zzbkf, this.zzbkg, this.zzbkh, this.zzbki});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zza)) {
         return false;
      } else {
         zza var2 = (zza)var1;
         return zzbe.equal(this.zzbjI, var2.zzbjI) && zzbe.equal(this.zzbjj, var2.zzbjj) && zzbe.equal(this.zzbke, var2.zzbke) && zzbe.equal(this.zzbkc, var2.zzbkc) && zzbe.equal(this.zzbkd, var2.zzbkd) && zzbe.equal(this.zzbkf, var2.zzbkf) && zzbe.equal(this.zzbkg, var2.zzbkg) && zzbe.equal(this.zzbkh, var2.zzbkh) && zzbe.equal(this.zzbki, var2.zzbki);
      }
   }

   public final boolean isDataValid() {
      return true;
   }
}
