package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class MaskedWallet extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzq();
   String zzbOq;
   String zzbOr;
   String[] zzbOw;
   String zzbOt;
   private zza zzbOu;
   private zza zzbOv;
   private LoyaltyWalletObject[] zzbPo;
   private OfferWalletObject[] zzbPp;
   UserAddress zzbOx;
   UserAddress zzbOy;
   InstrumentInfo[] zzbOz;

   public static MaskedWallet.Builder newBuilderFrom(MaskedWallet var0) {
      zzbo.zzu(var0);
      MaskedWallet.Builder var10000 = (new MaskedWallet().new Builder((zzp)null)).setGoogleTransactionId(var0.getGoogleTransactionId()).setMerchantTransactionId(var0.getMerchantTransactionId()).setPaymentDescriptions(var0.getPaymentDescriptions()).setInstrumentInfos(var0.getInstrumentInfos()).setEmail(var0.getEmail());
      LoyaltyWalletObject[] var2 = var0.zzbPo;
      MaskedWallet.Builder var1 = var10000;
      var10000.zzbPq.zzbPo = var2;
      OfferWalletObject[] var3 = var0.zzbPp;
      var1.zzbPq.zzbPp = var3;
      return var1.setBuyerBillingAddress(var0.getBuyerBillingAddress()).setBuyerShippingAddress(var0.getBuyerShippingAddress());
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbOq, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbOr, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbOw, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbOt, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbOu, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbOv, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbPo, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbPp, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzbOx, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzbOy, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzbOz, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   MaskedWallet(String var1, String var2, String[] var3, String var4, zza var5, zza var6, LoyaltyWalletObject[] var7, OfferWalletObject[] var8, UserAddress var9, UserAddress var10, InstrumentInfo[] var11) {
      this.zzbOq = var1;
      this.zzbOr = var2;
      this.zzbOw = var3;
      this.zzbOt = var4;
      this.zzbOu = var5;
      this.zzbOv = var6;
      this.zzbPo = var7;
      this.zzbPp = var8;
      this.zzbOx = var9;
      this.zzbOy = var10;
      this.zzbOz = var11;
   }

   private MaskedWallet() {
   }

   public final String getGoogleTransactionId() {
      return this.zzbOq;
   }

   public final String getMerchantTransactionId() {
      return this.zzbOr;
   }

   public final String[] getPaymentDescriptions() {
      return this.zzbOw;
   }

   public final InstrumentInfo[] getInstrumentInfos() {
      return this.zzbOz;
   }

   public final String getEmail() {
      return this.zzbOt;
   }

   public final UserAddress getBuyerBillingAddress() {
      return this.zzbOx;
   }

   public final UserAddress getBuyerShippingAddress() {
      return this.zzbOy;
   }

   public final class Builder {
      private Builder() {
      }

      public final MaskedWallet.Builder setGoogleTransactionId(String var1) {
         MaskedWallet.this.zzbOq = var1;
         return this;
      }

      public final MaskedWallet.Builder setMerchantTransactionId(String var1) {
         MaskedWallet.this.zzbOr = var1;
         return this;
      }

      public final MaskedWallet.Builder setPaymentDescriptions(String[] var1) {
         MaskedWallet.this.zzbOw = var1;
         return this;
      }

      public final MaskedWallet.Builder setInstrumentInfos(InstrumentInfo[] var1) {
         MaskedWallet.this.zzbOz = var1;
         return this;
      }

      public final MaskedWallet.Builder setEmail(String var1) {
         MaskedWallet.this.zzbOt = var1;
         return this;
      }

      public final MaskedWallet.Builder setBuyerBillingAddress(UserAddress var1) {
         MaskedWallet.this.zzbOx = var1;
         return this;
      }

      public final MaskedWallet.Builder setBuyerShippingAddress(UserAddress var1) {
         MaskedWallet.this.zzbOy = var1;
         return this;
      }

      public final MaskedWallet build() {
         return MaskedWallet.this;
      }

      // $FF: synthetic method
      Builder(zzp var2) {
         this();
      }
   }
}
