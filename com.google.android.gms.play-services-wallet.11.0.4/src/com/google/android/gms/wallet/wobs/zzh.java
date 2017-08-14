package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzh extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzi();
   private int zzbQL;
   private String zzbQM;
   private double zzbQN;
   private String zzbOH;
   private long zzbQO;
   private int zzbQP;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbQL);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbQM, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbQN);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbOH, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbQO);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzbQP);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   zzh(int var1, String var2, double var3, String var5, long var6, int var8) {
      this.zzbQL = var1;
      this.zzbQM = var2;
      this.zzbQN = var3;
      this.zzbOH = var5;
      this.zzbQO = var6;
      this.zzbQP = var8;
   }

   zzh() {
      this.zzbQP = -1;
      this.zzbQL = -1;
      this.zzbQN = -1.0D;
   }
}
