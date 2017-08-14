package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcr extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzcq();
   private int statusCode;
   private boolean zzbSK;
   private boolean zzbSL;

   public zzcr(int var1, boolean var2, boolean var3) {
      this.statusCode = var1;
      this.zzbSK = var2;
      this.zzbSL = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.statusCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbSK);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbSL);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
