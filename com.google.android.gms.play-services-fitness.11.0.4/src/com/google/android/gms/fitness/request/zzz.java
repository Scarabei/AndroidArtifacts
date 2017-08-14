package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;

public final class zzz extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzaa();

   zzz(int var1, IBinder var2) {
      this.zzaku = var1;
      this.zzaWo = zzbxh.zzW(var2);
   }

   public zzz(zzbxg var1) {
      this.zzaku = 2;
      this.zzaWo = var1;
   }

   public final String toString() {
      return String.format("DisableFitRequest");
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
