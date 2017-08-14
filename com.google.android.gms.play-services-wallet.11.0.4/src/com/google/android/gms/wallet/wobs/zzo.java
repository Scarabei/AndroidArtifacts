package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzo extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzp();
   private String zzbQT;
   private String description;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbQT, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.description, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zzo(String var1, String var2) {
      this.zzbQT = var1;
      this.description = var2;
   }

   zzo() {
   }
}
