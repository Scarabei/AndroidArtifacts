package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;

public final class zzbk extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final String zzaWm;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzbl();

   zzbk(int var1, String var2, IBinder var3) {
      this.zzaku = var1;
      this.zzaWm = var2;
      this.zzaWo = zzbxh.zzW(var3);
   }

   public zzbk(String var1, zzbxg var2) {
      this.zzaku = 5;
      this.zzaWm = var1;
      this.zzaWo = var2;
   }

   public final String toString() {
      return String.format("UnclaimBleDeviceRequest{%s}", this.zzaWm);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaWm, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
