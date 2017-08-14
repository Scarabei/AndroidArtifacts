package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzct extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzcs();
   private int statusCode;
   private boolean enabled;

   public zzct(int var1, boolean var2) {
      this.statusCode = var1;
      this.enabled = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.statusCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.enabled);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
