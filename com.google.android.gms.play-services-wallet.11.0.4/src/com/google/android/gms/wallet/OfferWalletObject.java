package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class OfferWalletObject extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzv();
   private final int zzaku;
   private String zzbPH;
   private CommonWalletObject zzbOD;

   public final int getVersionCode() {
      return this.zzaku;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getVersionCode());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, (String)null, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbPH, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbOD, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   OfferWalletObject(int var1, String var2, String var3, CommonWalletObject var4) {
      this.zzaku = var1;
      this.zzbPH = var3;
      if (var1 < 3) {
         this.zzbOD = CommonWalletObject.zzDU().zzgi(var2).zzDV();
      } else {
         this.zzbOD = var4;
      }
   }

   OfferWalletObject() {
      this.zzaku = 3;
   }

   public final String getId() {
      return this.zzbOD.getId();
   }

   public final String getRedemptionCode() {
      return this.zzbPH;
   }
}
