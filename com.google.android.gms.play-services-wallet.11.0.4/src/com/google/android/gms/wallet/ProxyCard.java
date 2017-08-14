package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/** @deprecated */
@Deprecated
public final class ProxyCard extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzz();
   private String zzbPM;
   private String zzbPN;
   private int zzbPO;
   private int zzbPP;

   public ProxyCard(String var1, String var2, int var3, int var4) {
      this.zzbPM = var1;
      this.zzbPN = var2;
      this.zzbPO = var3;
      this.zzbPP = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbPM, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbPN, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbPO);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzbPP);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String getPan() {
      return this.zzbPM;
   }

   public final String getCvn() {
      return this.zzbPN;
   }

   public final int getExpirationMonth() {
      return this.zzbPO;
   }

   public final int getExpirationYear() {
      return this.zzbPP;
   }
}
