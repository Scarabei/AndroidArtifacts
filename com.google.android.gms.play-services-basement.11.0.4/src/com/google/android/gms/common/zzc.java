package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzc extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzd();
   private String name;
   private int version;

   public zzc(String var1, int var2) {
      this.name = var1;
      this.version = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, (String)this.name, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.version);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
