package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzc extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzd();
   private String label;
   private String value;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.label, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.value, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zzc(String var1, String var2) {
      this.label = var1;
      this.value = var2;
   }

   zzc() {
   }
}
