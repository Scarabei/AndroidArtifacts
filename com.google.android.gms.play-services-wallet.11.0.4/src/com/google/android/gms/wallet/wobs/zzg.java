package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzg extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzj();
   private String label;
   private zzh zzbQK;
   private String type;
   private zzm zzbPe;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.label, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbQK, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.type, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbPe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   zzg(String var1, zzh var2, String var3, zzm var4) {
      this.label = var1;
      this.zzbQK = var2;
      this.type = var3;
      this.zzbPe = var4;
   }

   zzg() {
   }
}
