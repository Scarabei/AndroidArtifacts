package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;

public final class zzbg extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final zzad zzaXp;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzbh();

   zzbg(int var1, IBinder var2, IBinder var3) {
      this.zzaku = var1;
      IInterface var5;
      this.zzaXp = (zzad)(var2 == null ? null : ((var5 = var2.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback")) instanceof zzad ? (zzad)var5 : new zzaf(var2)));
      this.zzaWo = zzbxh.zzW(var3);
   }

   private zzbg(zzad var1, zzbxg var2) {
      this.zzaku = 3;
      this.zzaXp = var1;
      this.zzaWo = var2;
   }

   public zzbg(BleScanCallback var1, zzbxg var2) {
      this((zzad)zzc.zztT().zzb(var1), var2);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaXp.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
