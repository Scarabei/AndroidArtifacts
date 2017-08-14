package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbpz extends zza {
   public static final Creator CREATOR = new zzbqa();
   private boolean zzaNP;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaNP);
      zzd.zzI(var1, var5);
   }

   public zzbpz(boolean var1) {
      this.zzaNP = var1;
   }
}
