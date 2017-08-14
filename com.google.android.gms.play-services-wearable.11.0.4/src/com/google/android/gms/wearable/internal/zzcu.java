package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.wearable.ConnectionConfiguration;

/** @deprecated */
@Deprecated
public final class zzcu extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzcv();
   private int statusCode;
   private ConnectionConfiguration zzbSM;

   public zzcu(int var1, ConnectionConfiguration var2) {
      this.statusCode = var1;
      this.zzbSM = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.statusCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbSM, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
