package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public final class zzabi extends zza {
   public static final Creator CREATOR = new zzabj();
   String zzHg;

   public zzabi(String var1) {
      this.zzHg = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzHg, false);
      zzd.zzI(var1, var5);
   }
}
