package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzek extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzel();
   private String packageName;
   private String label;
   private long zzbTc;

   public zzek(String var1, String var2, long var3) {
      this.packageName = var1;
      this.label = var2;
      this.zzbTc = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.packageName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.label, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbTc);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
