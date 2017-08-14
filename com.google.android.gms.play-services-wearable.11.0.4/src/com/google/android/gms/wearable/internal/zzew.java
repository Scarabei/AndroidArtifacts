package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

public final class zzew extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzex();
   private int statusCode;
   private long zzbTc;
   private List zzbTe;

   public zzew(int var1, long var2, List var4) {
      this.statusCode = var1;
      this.zzbTc = var2;
      this.zzbTe = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.statusCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbTc);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbTe, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
