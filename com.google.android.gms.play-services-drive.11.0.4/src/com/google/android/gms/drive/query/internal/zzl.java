package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzl extends zza {
   public static final Creator CREATOR = new zzm();
   private String mValue;

   public zzl(String var1) {
      this.mValue = var1;
   }

   public final Object zza(zzj var1) {
      return var1.zzcU(this.mValue);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.mValue, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
