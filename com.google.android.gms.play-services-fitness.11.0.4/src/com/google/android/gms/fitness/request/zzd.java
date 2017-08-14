package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;

public final class zzd extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final String zzaWm;
   private final BleDevice zzaWn;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zze();

   zzd(int var1, String var2, BleDevice var3, IBinder var4) {
      this.zzaku = var1;
      this.zzaWm = var2;
      this.zzaWn = var3;
      this.zzaWo = zzbxh.zzW(var4);
   }

   public zzd(String var1, BleDevice var2, zzbxg var3) {
      this.zzaku = 4;
      this.zzaWm = var1;
      this.zzaWn = var2;
      this.zzaWo = var3;
   }

   public final String toString() {
      return String.format("ClaimBleDeviceRequest{%s %s}", this.zzaWm, this.zzaWn);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaWm, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaWn, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
