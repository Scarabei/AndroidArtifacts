package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzeo extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzep();
   private int zzaku;
   private zzdk zzbRJ;

   zzeo(int var1, IBinder var2) {
      this.zzaku = var1;
      if (var2 != null) {
         IInterface var4;
         this.zzbRJ = (zzdk)(var2 == null ? null : ((var4 = var2.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener")) instanceof zzdk ? (zzdk)var4 : new zzdm(var2)));
      } else {
         this.zzbRJ = null;
      }
   }

   public zzeo(zzdk var1) {
      this.zzaku = 1;
      this.zzbRJ = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbRJ == null ? null : this.zzbRJ.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
