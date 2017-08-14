package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;

public final class FullWalletRequest extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzh();
   String zzbOq;
   String zzbOr;
   Cart zzbOB;

   public static FullWalletRequest.Builder newBuilder() {
      return new FullWalletRequest().new Builder((zzg)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbOq, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbOr, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbOB, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   FullWalletRequest(String var1, String var2, Cart var3) {
      this.zzbOq = var1;
      this.zzbOr = var2;
      this.zzbOB = var3;
   }

   FullWalletRequest() {
   }

   public final String getGoogleTransactionId() {
      return this.zzbOq;
   }

   public final String getMerchantTransactionId() {
      return this.zzbOr;
   }

   public final Cart getCart() {
      return this.zzbOB;
   }

   public final class Builder {
      // $FF: synthetic field
      private FullWalletRequest zzbOC;

      private Builder() {
         this.zzbOC = FullWalletRequest.this;
         super();
      }

      public final FullWalletRequest.Builder setGoogleTransactionId(String var1) {
         this.zzbOC.zzbOq = var1;
         return this;
      }

      public final FullWalletRequest.Builder setMerchantTransactionId(String var1) {
         this.zzbOC.zzbOr = var1;
         return this;
      }

      public final FullWalletRequest.Builder setCart(Cart var1) {
         this.zzbOC.zzbOB = var1;
         return this;
      }

      public final FullWalletRequest build() {
         return this.zzbOC;
      }

      // $FF: synthetic method
      Builder(zzg var2) {
         this();
      }
   }
}
