package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzaz extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzba();
   public final int statusCode;

   public zzaz(int var1) {
      this.statusCode = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.statusCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
