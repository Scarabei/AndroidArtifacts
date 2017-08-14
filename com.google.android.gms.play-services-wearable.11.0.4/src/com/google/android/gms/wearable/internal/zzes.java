package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzes extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzet();
   public final int statusCode;
   public final int zzbhd;

   public zzes(int var1, int var2) {
      this.statusCode = var1;
      this.zzbhd = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.statusCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbhd);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
