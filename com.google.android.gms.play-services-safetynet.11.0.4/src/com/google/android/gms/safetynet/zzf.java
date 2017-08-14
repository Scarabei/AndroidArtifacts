package com.google.android.gms.safetynet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzf extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzg();
   private final String zzakv;

   public zzf(String var1) {
      this.zzakv = var1;
   }

   public final String getTokenResult() {
      return this.zzakv;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzakv, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
