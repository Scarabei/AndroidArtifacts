package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;

public final class PaymentMethodTokenizationParameters extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzy();
   int zzbPI;
   Bundle zzbPK = new Bundle();

   public static PaymentMethodTokenizationParameters.Builder newBuilder() {
      return new PaymentMethodTokenizationParameters().new Builder((zzx)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbPI);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbPK, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   PaymentMethodTokenizationParameters(int var1, Bundle var2) {
      this.zzbPI = var1;
      this.zzbPK = var2;
   }

   private PaymentMethodTokenizationParameters() {
   }

   public final int getPaymentMethodTokenizationType() {
      return this.zzbPI;
   }

   public final Bundle getParameters() {
      return new Bundle(this.zzbPK);
   }

   public final class Builder {
      // $FF: synthetic field
      private PaymentMethodTokenizationParameters zzbPL;

      private Builder() {
         this.zzbPL = PaymentMethodTokenizationParameters.this;
         super();
      }

      public final PaymentMethodTokenizationParameters.Builder setPaymentMethodTokenizationType(int var1) {
         this.zzbPL.zzbPI = var1;
         return this;
      }

      public final PaymentMethodTokenizationParameters.Builder addParameter(String var1, String var2) {
         zzbo.zzh(var1, "Tokenization parameter name must not be empty");
         zzbo.zzh(var2, "Tokenization parameter value must not be empty");
         this.zzbPL.zzbPK.putString(var1, var2);
         return this;
      }

      public final PaymentMethodTokenizationParameters build() {
         return this.zzbPL;
      }

      // $FF: synthetic method
      Builder(zzx var2) {
         this();
      }
   }
}
