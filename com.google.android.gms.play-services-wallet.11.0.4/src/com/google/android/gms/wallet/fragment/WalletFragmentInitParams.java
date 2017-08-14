package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class WalletFragmentInitParams extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzd();
   private String zzakh;
   private MaskedWalletRequest zzbQf;
   private int zzbQt;
   private MaskedWallet zzbQg;

   public static WalletFragmentInitParams.Builder newBuilder() {
      return new WalletFragmentInitParams().new Builder((zzc)null);
   }

   private WalletFragmentInitParams() {
      this.zzbQt = -1;
   }

   WalletFragmentInitParams(String var1, MaskedWalletRequest var2, int var3, MaskedWallet var4) {
      this.zzakh = var1;
      this.zzbQf = var2;
      this.zzbQt = var3;
      this.zzbQg = var4;
   }

   public final String getAccountName() {
      return this.zzakh;
   }

   public final MaskedWalletRequest getMaskedWalletRequest() {
      return this.zzbQf;
   }

   public final int getMaskedWalletRequestCode() {
      return this.zzbQt;
   }

   public final MaskedWallet getMaskedWallet() {
      return this.zzbQg;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getAccountName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getMaskedWalletRequest(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getMaskedWalletRequestCode());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getMaskedWallet(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final class Builder {
      // $FF: synthetic field
      private WalletFragmentInitParams zzbQu;

      private Builder() {
         this.zzbQu = WalletFragmentInitParams.this;
         super();
      }

      public final WalletFragmentInitParams.Builder setAccountName(String var1) {
         this.zzbQu.zzakh = var1;
         return this;
      }

      public final WalletFragmentInitParams.Builder setMaskedWalletRequest(MaskedWalletRequest var1) {
         this.zzbQu.zzbQf = var1;
         return this;
      }

      public final WalletFragmentInitParams.Builder setMaskedWalletRequestCode(int var1) {
         this.zzbQu.zzbQt = var1;
         return this;
      }

      public final WalletFragmentInitParams.Builder setMaskedWallet(MaskedWallet var1) {
         this.zzbQu.zzbQg = var1;
         return this;
      }

      public final WalletFragmentInitParams build() {
         zzbo.zza(this.zzbQu.zzbQg != null && this.zzbQu.zzbQf == null || this.zzbQu.zzbQg == null && this.zzbQu.zzbQf != null, "Exactly one of MaskedWallet or MaskedWalletRequest is required");
         zzbo.zza(this.zzbQu.zzbQt >= 0, "masked wallet request code is required and must be non-negative");
         return this.zzbQu;
      }

      // $FF: synthetic method
      Builder(zzc var2) {
         this();
      }
   }
}
