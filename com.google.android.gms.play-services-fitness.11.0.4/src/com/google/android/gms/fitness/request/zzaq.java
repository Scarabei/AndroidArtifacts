package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;

public final class zzaq extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final com.google.android.gms.fitness.data.zzt zzaWV;
   private final PendingIntent mPendingIntent;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzar();

   zzaq(int var1, IBinder var2, PendingIntent var3, IBinder var4) {
      this.zzaku = var1;
      this.zzaWV = var2 == null ? null : com.google.android.gms.fitness.data.zzu.zzN(var2);
      this.mPendingIntent = var3;
      this.zzaWo = zzbxh.zzW(var4);
   }

   public zzaq(com.google.android.gms.fitness.data.zzt var1, PendingIntent var2, zzbxg var3) {
      this.zzaku = 4;
      this.zzaWV = var1;
      this.mPendingIntent = var2;
      this.zzaWo = var3;
   }

   public final String toString() {
      return String.format("SensorUnregistrationRequest{%s}", this.zzaWV);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaWV == null ? null : this.zzaWV.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.mPendingIntent, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
