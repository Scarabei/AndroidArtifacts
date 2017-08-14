package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;

public final class zzbi extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private Subscription zzaXr;
   private final boolean zzaXs;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzbj();

   zzbi(int var1, Subscription var2, boolean var3, IBinder var4) {
      this.zzaku = var1;
      this.zzaXr = var2;
      this.zzaXs = var3;
      this.zzaWo = zzbxh.zzW(var4);
   }

   public zzbi(Subscription var1, boolean var2, zzbxg var3) {
      this.zzaku = 3;
      this.zzaXr = var1;
      this.zzaXs = false;
      this.zzaWo = var3;
   }

   public final String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("subscription", this.zzaXr).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaXr, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaXs);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
