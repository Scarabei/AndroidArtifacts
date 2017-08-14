package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzab extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzac();
   private String zzbPW;

   zzab(String var1) {
      this.zzbPW = var1;
   }

   private zzab() {
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbPW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
