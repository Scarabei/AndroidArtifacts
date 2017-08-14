package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.Locale;

public final class zzat extends com.google.android.gms.common.internal.safeparcel.zza {
   private static zzat zzbld = new zzat("com.google.android.gms", Locale.getDefault(), (String)null);
   public static final Creator CREATOR = new zzau();
   private String zzble;
   private String zzblf;
   private String zzaLx;
   private String zzbjT;
   private int zzblg;
   private int zzblh;

   public zzat(String var1, String var2, String var3, String var4, int var5, int var6) {
      this.zzble = var1;
      this.zzblf = var2;
      this.zzaLx = var3;
      this.zzbjT = var4;
      this.zzblg = var5;
      this.zzblh = var6;
   }

   private zzat(String var1, Locale var2, String var3) {
      this(var1, var2.toString(), (String)null, (String)null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
   }

   public zzat(String var1, Locale var2, String var3, String var4, int var5) {
      this(var1, var2.toString(), var3, var4, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, var5);
   }

   @SuppressLint({"DefaultLocale"})
   public final String toString() {
      return zzbe.zzt(this).zzg("clientPackageName", this.zzble).zzg("locale", this.zzblf).zzg("accountName", this.zzaLx).zzg("gCoreClientName", this.zzbjT).toString();
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzble, this.zzblf, this.zzaLx, this.zzbjT, this.zzblg, this.zzblh});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && var1 instanceof zzat) {
         zzat var2 = (zzat)var1;
         return this.zzblg == var2.zzblg && this.zzblh == var2.zzblh && this.zzblf.equals(var2.zzblf) && this.zzble.equals(var2.zzble) && zzbe.equal(this.zzaLx, var2.zzaLx) && zzbe.equal(this.zzbjT, var2.zzbjT);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzble, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzblf, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaLx, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbjT, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzblg);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzblh);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
