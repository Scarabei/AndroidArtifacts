package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzq extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzr();
   private String zzbQQ;
   private String body;
   private zzm zzbQU;
   private zzo zzbQV;
   private zzo zzbQW;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbQQ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.body, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbQU, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbQV, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbQW, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   zzq(String var1, String var2, zzm var3, zzo var4, zzo var5) {
      this.zzbQQ = var1;
      this.body = var2;
      this.zzbQU = var3;
      this.zzbQV = var4;
      this.zzbQW = var5;
   }

   zzq() {
   }
}
