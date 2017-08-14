package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzayf extends zza {
   public static final Creator CREATOR = new zzayg();
   private double zzaqD;
   private boolean zzaqE;
   private int zzaxT;
   private ApplicationMetadata zzaye;
   private int zzaxU;

   zzayf(double var1, boolean var3, int var4, ApplicationMetadata var5, int var6) {
      this.zzaqD = var1;
      this.zzaqE = var3;
      this.zzaxT = var4;
      this.zzaye = var5;
      this.zzaxU = var6;
   }

   public zzayf() {
      this(Double.NaN, false, -1, (ApplicationMetadata)null, -1);
   }

   public final double getVolume() {
      return this.zzaqD;
   }

   public final boolean zzoJ() {
      return this.zzaqE;
   }

   public final int getActiveInputState() {
      return this.zzaxT;
   }

   public final int getStandbyState() {
      return this.zzaxU;
   }

   public final ApplicationMetadata getApplicationMetadata() {
      return this.zzaye;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaqD);
      zzd.zza(var1, 3, this.zzaqE);
      zzd.zzc(var1, 4, this.zzaxT);
      zzd.zza(var1, 5, this.zzaye, var2, false);
      zzd.zzc(var1, 6, this.zzaxU);
      zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzayf)) {
         return false;
      } else {
         zzayf var2 = (zzayf)var1;
         return this.zzaqD == var2.zzaqD && this.zzaqE == var2.zzaqE && this.zzaxT == var2.zzaxT && com.google.android.gms.internal.zzaye.zza(this.zzaye, var2.zzaye) && this.zzaxU == var2.zzaxU;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaqD, this.zzaqE, this.zzaxT, this.zzaye, this.zzaxU});
   }
}
