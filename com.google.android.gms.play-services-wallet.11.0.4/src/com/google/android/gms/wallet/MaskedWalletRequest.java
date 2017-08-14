package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.Collection;

public final class MaskedWalletRequest extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzs();
   String zzbOr;
   boolean zzbPr;
   boolean zzbPs;
   boolean zzbPt;
   String zzbPu;
   String zzbOn;
   String zzbPv;
   Cart zzbOB;
   private boolean zzbPw;
   boolean zzbPx;
   private CountrySpecification[] zzbPy;
   boolean zzbPz;
   boolean zzbPA;
   ArrayList zzbPB;
   PaymentMethodTokenizationParameters zzbPC;
   ArrayList zzbPD;
   String zzVJ;

   public static MaskedWalletRequest.Builder newBuilder() {
      return new MaskedWalletRequest().new Builder((zzr)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbOr, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbPr);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbPs);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbPt);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbPu, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbOn, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbPv, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbOB, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzbPw);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzbPx);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzbPy, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.zzbPz);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.zzbPA);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 15, this.zzbPB, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 16, this.zzbPC, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 17, this.zzbPD, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 18, this.zzVJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   MaskedWalletRequest(String var1, boolean var2, boolean var3, boolean var4, String var5, String var6, String var7, Cart var8, boolean var9, boolean var10, CountrySpecification[] var11, boolean var12, boolean var13, ArrayList var14, PaymentMethodTokenizationParameters var15, ArrayList var16, String var17) {
      this.zzbOr = var1;
      this.zzbPr = var2;
      this.zzbPs = var3;
      this.zzbPt = var4;
      this.zzbPu = var5;
      this.zzbOn = var6;
      this.zzbPv = var7;
      this.zzbOB = var8;
      this.zzbPw = var9;
      this.zzbPx = var10;
      this.zzbPy = var11;
      this.zzbPz = var12;
      this.zzbPA = var13;
      this.zzbPB = var14;
      this.zzbPC = var15;
      this.zzbPD = var16;
      this.zzVJ = var17;
   }

   MaskedWalletRequest() {
      this.zzbPz = true;
      this.zzbPA = true;
   }

   public final String getMerchantTransactionId() {
      return this.zzbOr;
   }

   public final boolean isPhoneNumberRequired() {
      return this.zzbPr;
   }

   public final boolean isShippingAddressRequired() {
      return this.zzbPs;
   }

   /** @deprecated */
   @Deprecated
   public final boolean useMinimalBillingAddress() {
      return this.zzbPt;
   }

   public final String getEstimatedTotalPrice() {
      return this.zzbPu;
   }

   public final String getCurrencyCode() {
      return this.zzbOn;
   }

   public final String getMerchantName() {
      return this.zzbPv;
   }

   public final Cart getCart() {
      return this.zzbOB;
   }

   /** @deprecated */
   @Deprecated
   public final boolean isBillingAgreement() {
      return this.zzbPx;
   }

   public final CountrySpecification[] getAllowedShippingCountrySpecifications() {
      return this.zzbPy;
   }

   public final boolean allowPrepaidCard() {
      return this.zzbPz;
   }

   public final boolean allowDebitCard() {
      return this.zzbPA;
   }

   public final ArrayList getAllowedCountrySpecificationsForShipping() {
      return this.zzbPB;
   }

   public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
      return this.zzbPC;
   }

   public final ArrayList getAllowedCardNetworks() {
      return this.zzbPD;
   }

   public final String getCountryCode() {
      return this.zzVJ;
   }

   public final class Builder {
      // $FF: synthetic field
      private MaskedWalletRequest zzbPE;

      private Builder() {
         this.zzbPE = MaskedWalletRequest.this;
         super();
      }

      public final MaskedWalletRequest.Builder setMerchantTransactionId(String var1) {
         this.zzbPE.zzbOr = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder setPhoneNumberRequired(boolean var1) {
         this.zzbPE.zzbPr = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder setShippingAddressRequired(boolean var1) {
         this.zzbPE.zzbPs = var1;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public final MaskedWalletRequest.Builder setUseMinimalBillingAddress(boolean var1) {
         this.zzbPE.zzbPt = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder setEstimatedTotalPrice(String var1) {
         this.zzbPE.zzbPu = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder setCurrencyCode(String var1) {
         this.zzbPE.zzbOn = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder setMerchantName(String var1) {
         this.zzbPE.zzbPv = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder setCart(Cart var1) {
         this.zzbPE.zzbOB = var1;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public final MaskedWalletRequest.Builder setIsBillingAgreement(boolean var1) {
         this.zzbPE.zzbPx = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder setAllowPrepaidCard(boolean var1) {
         this.zzbPE.zzbPz = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder setAllowDebitCard(boolean var1) {
         this.zzbPE.zzbPA = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder addAllowedCountrySpecificationForShipping(com.google.android.gms.identity.intents.model.CountrySpecification var1) {
         if (this.zzbPE.zzbPB == null) {
            this.zzbPE.zzbPB = new ArrayList();
         }

         this.zzbPE.zzbPB.add(var1);
         return this;
      }

      public final MaskedWalletRequest.Builder addAllowedCountrySpecificationsForShipping(Collection var1) {
         if (var1 != null) {
            if (this.zzbPE.zzbPB == null) {
               this.zzbPE.zzbPB = new ArrayList();
            }

            this.zzbPE.zzbPB.addAll(var1);
         }

         return this;
      }

      public final MaskedWalletRequest.Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters var1) {
         this.zzbPE.zzbPC = var1;
         return this;
      }

      public final MaskedWalletRequest.Builder addAllowedCardNetwork(int var1) {
         if (this.zzbPE.zzbPD == null) {
            this.zzbPE.zzbPD = new ArrayList();
         }

         this.zzbPE.zzbPD.add(var1);
         return this;
      }

      public final MaskedWalletRequest.Builder addAllowedCardNetworks(Collection var1) {
         if (var1 != null) {
            if (this.zzbPE.zzbPD == null) {
               this.zzbPE.zzbPD = new ArrayList();
            }

            this.zzbPE.zzbPD.addAll(var1);
         }

         return this;
      }

      public final MaskedWalletRequest.Builder setCountryCode(String var1) {
         this.zzbPE.zzVJ = var1;
         return this;
      }

      public final MaskedWalletRequest build() {
         return this.zzbPE;
      }

      // $FF: synthetic method
      Builder(zzr var2) {
         this();
      }
   }
}
