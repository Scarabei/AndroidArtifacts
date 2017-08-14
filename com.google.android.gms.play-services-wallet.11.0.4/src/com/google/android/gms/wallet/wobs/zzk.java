package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzk extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzl();
   private String zzbQQ;
   private String body;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbQQ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.body, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zzk(String var1, String var2) {
      this.zzbQQ = var1;
      this.body = var2;
   }

   zzk() {
   }
}
