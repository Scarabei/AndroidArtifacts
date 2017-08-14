package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzm extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzn();
   private long zzbQR;
   private long zzbQS;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbQR);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbQS);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zzm(long var1, long var3) {
      this.zzbQR = var1;
      this.zzbQS = var3;
   }

   zzm() {
   }
}
