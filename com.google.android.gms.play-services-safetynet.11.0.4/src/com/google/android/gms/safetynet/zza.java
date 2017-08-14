package com.google.android.gms.safetynet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zza extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private final String zzbBF;

   public zza(String var1) {
      this.zzbBF = var1;
   }

   public final String getJwsResult() {
      return this.zzbBF;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbBF, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
