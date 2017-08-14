package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlaceFilter extends zza {
   public static final Creator CREATOR = new zzh();
   private static final PlaceFilter zzbjt = new PlaceFilter();
   private List zzbju;
   private boolean zzbjv;
   private List zzbjw;
   private List zzbjx;
   private final Set zzbjy;
   private final Set zzbjz;
   private final Set zzbjA;

   public PlaceFilter() {
      this(false, (Collection)null);
   }

   public PlaceFilter(boolean var1, @Nullable Collection var2) {
      this((Collection)null, var1, (Collection)var2, (Collection)null);
   }

   private PlaceFilter(@Nullable Collection var1, boolean var2, @Nullable Collection var3, @Nullable Collection var4) {
      this(zzh((Collection)null), var2, zzh(var3), zzh((Collection)null));
   }

   PlaceFilter(@Nullable List var1, boolean var2, @Nullable List var3, @Nullable List var4) {
      this.zzbju = var1 == null ? Collections.emptyList() : Collections.unmodifiableList(var1);
      this.zzbjv = var2;
      this.zzbjw = var4 == null ? Collections.emptyList() : Collections.unmodifiableList(var4);
      this.zzbjx = var3 == null ? Collections.emptyList() : Collections.unmodifiableList(var3);
      this.zzbjy = zzC(this.zzbju);
      this.zzbjz = zzC(this.zzbjw);
      this.zzbjA = zzC(this.zzbjx);
   }

   public final Set getPlaceIds() {
      return this.zzbjA;
   }

   public final boolean isRestrictedToPlacesOpenNow() {
      return this.zzbjv;
   }

   public final String toString() {
      zzbg var1 = zzbe.zzt(this);
      if (!this.zzbjy.isEmpty()) {
         var1.zzg("types", this.zzbjy);
      }

      var1.zzg("requireOpenNow", this.zzbjv);
      if (!this.zzbjA.isEmpty()) {
         var1.zzg("placeIds", this.zzbjA);
      }

      if (!this.zzbjz.isEmpty()) {
         var1.zzg("requestedUserDataTypes", this.zzbjz);
      }

      return var1.toString();
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbjy, this.zzbjv, this.zzbjz, this.zzbjA});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof PlaceFilter)) {
         return false;
      } else {
         PlaceFilter var2 = (PlaceFilter)var1;
         return this.zzbjy.equals(var2.zzbjy) && this.zzbjv == var2.zzbjv && this.zzbjz.equals(var2.zzbjz) && this.zzbjA.equals(var2.zzbjA);
      }
   }

   /** @deprecated */
   @Deprecated
   public static PlaceFilter zzvS() {
      new PlaceFilter.zza((zzg)null);
      return new PlaceFilter((List)null, false, (List)null, (List)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbju, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbjv);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbjw, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 6, this.zzbjx, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   /** @deprecated */
   @Deprecated
   public static final class zza {
      private Collection zzbjB;
      private boolean zzbjv;
      private Collection zzbjC;
      private String[] zzbjD;

      private zza() {
         this.zzbjB = null;
         this.zzbjv = false;
         this.zzbjC = null;
         this.zzbjD = null;
      }

      // $FF: synthetic method
      zza(zzg var1) {
         this();
      }
   }
}
