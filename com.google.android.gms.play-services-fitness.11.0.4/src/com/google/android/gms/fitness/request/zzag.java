package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbzk;
import com.google.android.gms.internal.zzbzl;

public final class zzag extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final zzbzk zzaWQ;
   public static final Creator CREATOR = new zzah();

   zzag(int var1, IBinder var2) {
      this.zzaku = var1;
      this.zzaWQ = zzbzl.zzX(var2);
   }

   public zzag(zzbzk var1) {
      this.zzaku = 2;
      this.zzaWQ = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaWQ.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
