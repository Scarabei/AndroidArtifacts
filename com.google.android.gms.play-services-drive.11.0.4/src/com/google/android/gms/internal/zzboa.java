package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzboa extends zza {
   public static final Creator CREATOR = new zzbob();
   final IBinder zzaOG;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaOG, false);
      zzd.zzI(var1, var5);
   }

   zzboa(IBinder var1) {
      this.zzaOG = var1;
   }
}
