package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

public final class zzcy extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzcz();
   public final int statusCode;
   public final List zzbSO;

   public zzcy(int var1, List var2) {
      this.statusCode = var1;
      this.zzbSO = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.statusCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbSO, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
