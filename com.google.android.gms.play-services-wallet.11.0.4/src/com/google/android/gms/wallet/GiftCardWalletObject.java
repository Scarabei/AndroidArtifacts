package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class GiftCardWalletObject extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzi();
   private CommonWalletObject zzbOD = CommonWalletObject.zzDU().zzDV();
   private String zzbOE;
   private String pin;
   private String zzbOF;
   private long zzbOG;
   private String zzbOH;
   private long zzbOI;
   private String zzbOJ;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbOD, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbOE, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.pin, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbOF, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbOG);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbOH, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbOI);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbOJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   GiftCardWalletObject() {
   }

   GiftCardWalletObject(CommonWalletObject var1, String var2, String var3, String var4, long var5, String var7, long var8, String var10) {
      this.zzbOD = var1;
      this.zzbOE = var2;
      this.pin = var3;
      this.zzbOG = var5;
      this.zzbOH = var7;
      this.zzbOI = var8;
      this.zzbOJ = var10;
      this.zzbOF = var4;
   }

   public final String getId() {
      return this.zzbOD.getId();
   }
}
