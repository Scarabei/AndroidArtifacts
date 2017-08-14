package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzg extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzh();
   private long zzaMf;
   private long zzaMg;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaMf);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaMg);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zzg(long var1, long var3) {
      this.zzaMf = var1;
      this.zzaMg = var3;
   }
}
