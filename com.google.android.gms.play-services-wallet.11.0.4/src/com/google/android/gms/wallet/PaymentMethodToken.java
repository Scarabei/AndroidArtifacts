package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class PaymentMethodToken extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzw();
   private int zzbPI;
   private String zzbPJ;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbPI);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbPJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   PaymentMethodToken(int var1, String var2) {
      this.zzbPI = var1;
      this.zzbPJ = var2;
   }

   private PaymentMethodToken() {
   }

   public final int getPaymentMethodTokenizationType() {
      return this.zzbPI;
   }

   public final String getToken() {
      return this.zzbPJ;
   }
}
