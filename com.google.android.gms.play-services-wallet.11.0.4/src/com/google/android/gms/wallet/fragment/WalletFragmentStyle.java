package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.R.style;
import com.google.android.gms.R.styleable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class WalletFragmentStyle extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzg();
   private Bundle zzbQx;
   private int zzbQy;

   public WalletFragmentStyle() {
      this.zzbQx = new Bundle();
      this.zzbQx.putInt("buyButtonAppearanceDefault", 4);
      this.zzbQx.putInt("maskedWalletDetailsLogoImageTypeDefault", 3);
   }

   WalletFragmentStyle(Bundle var1, int var2) {
      this.zzbQx = var1;
      this.zzbQy = var2;
   }

   public final WalletFragmentStyle setStyleResourceId(int var1) {
      this.zzbQy = var1;
      return this;
   }

   public final WalletFragmentStyle setBuyButtonText(int var1) {
      this.zzbQx.putInt("buyButtonText", var1);
      return this;
   }

   public final WalletFragmentStyle setBuyButtonHeight(int var1) {
      this.zzbQx.putLong("buyButtonHeight", zzbO(var1));
      return this;
   }

   public final WalletFragmentStyle setBuyButtonHeight(int var1, float var2) {
      this.zzbQx.putLong("buyButtonHeight", zzb(var1, var2));
      return this;
   }

   public final WalletFragmentStyle setBuyButtonWidth(int var1) {
      this.zzbQx.putLong("buyButtonWidth", zzbO(var1));
      return this;
   }

   public final WalletFragmentStyle setBuyButtonWidth(int var1, float var2) {
      this.zzbQx.putLong("buyButtonWidth", zzb(var1, var2));
      return this;
   }

   public final WalletFragmentStyle setBuyButtonAppearance(int var1) {
      this.zzbQx.putInt("buyButtonAppearance", var1);
      return this;
   }

   public final WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int var1) {
      this.zzbQx.putInt("maskedWalletDetailsTextAppearance", var1);
      return this;
   }

   public final WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int var1) {
      this.zzbQx.putInt("maskedWalletDetailsHeaderTextAppearance", var1);
      return this;
   }

   public final WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int var1) {
      this.zzbQx.remove("maskedWalletDetailsBackgroundResource");
      this.zzbQx.putInt("maskedWalletDetailsBackgroundColor", var1);
      return this;
   }

   public final WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int var1) {
      this.zzbQx.remove("maskedWalletDetailsBackgroundColor");
      this.zzbQx.putInt("maskedWalletDetailsBackgroundResource", var1);
      return this;
   }

   public final WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int var1) {
      this.zzbQx.putInt("maskedWalletDetailsButtonTextAppearance", var1);
      return this;
   }

   public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int var1) {
      this.zzbQx.remove("maskedWalletDetailsButtonBackgroundResource");
      this.zzbQx.putInt("maskedWalletDetailsButtonBackgroundColor", var1);
      return this;
   }

   public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int var1) {
      this.zzbQx.remove("maskedWalletDetailsButtonBackgroundColor");
      this.zzbQx.putInt("maskedWalletDetailsButtonBackgroundResource", var1);
      return this;
   }

   public final WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int var1) {
      this.zzbQx.putInt("maskedWalletDetailsLogoImageType", var1);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public final WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int var1) {
      this.zzbQx.putInt("maskedWalletDetailsLogoTextColor", var1);
      return this;
   }

   public final int zza(String var1, DisplayMetrics var2, int var3) {
      if (this.zzbQx.containsKey(var1)) {
         long var4;
         int var7 = (int)((var4 = this.zzbQx.getLong(var1)) >>> 32);
         int var8 = (int)var4;
         byte var9;
         switch(var7) {
         case 0:
            var9 = 0;
            break;
         case 1:
            var9 = 1;
            break;
         case 2:
            var9 = 2;
            break;
         case 3:
            var9 = 3;
            break;
         case 4:
            var9 = 4;
            break;
         case 5:
            var9 = 5;
            break;
         case 128:
            return TypedValue.complexToDimensionPixelSize(var8, var2);
         case 129:
            return var8;
         default:
            throw new IllegalStateException((new StringBuilder(36)).append("Unexpected unit or type: ").append(var7).toString());
         }

         return Math.round(TypedValue.applyDimension(var9, Float.intBitsToFloat(var8), var2));
      } else {
         return var3;
      }
   }

   private static long zzb(int var0, float var1) {
      switch(var0) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
         return zzl(var0, Float.floatToIntBits(var1));
      default:
         throw new IllegalArgumentException((new StringBuilder(30)).append("Unrecognized unit: ").append(var0).toString());
      }
   }

   private static long zzbO(int var0) {
      if (var0 < 0) {
         if (var0 != -1 && var0 != -2) {
            throw new IllegalArgumentException((new StringBuilder(39)).append("Unexpected dimension value: ").append(var0).toString());
         } else {
            return zzl(129, var0);
         }
      } else {
         return zzb(0, (float)var0);
      }
   }

   private static long zzl(int var0, int var1) {
      return (long)var0 << 32 | (long)var1 & 4294967295L;
   }

   public final void zzby(Context var1) {
      int var2 = this.zzbQy <= 0 ? style.WalletFragmentDefaultStyle : this.zzbQy;
      int[] var3 = styleable.WalletFragmentStyle;
      TypedArray var4 = var1.obtainStyledAttributes(var2, var3);
      this.zza(var4, styleable.WalletFragmentStyle_buyButtonWidth, "buyButtonWidth");
      this.zza(var4, styleable.WalletFragmentStyle_buyButtonHeight, "buyButtonHeight");
      this.zzb(var4, styleable.WalletFragmentStyle_buyButtonText, "buyButtonText");
      this.zzb(var4, styleable.WalletFragmentStyle_buyButtonAppearance, "buyButtonAppearance");
      this.zzb(var4, styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance, "maskedWalletDetailsTextAppearance");
      this.zzb(var4, styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance, "maskedWalletDetailsHeaderTextAppearance");
      this.zza(var4, styleable.WalletFragmentStyle_maskedWalletDetailsBackground, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
      this.zzb(var4, styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance, "maskedWalletDetailsButtonTextAppearance");
      this.zza(var4, styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
      this.zzb(var4, styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor, "maskedWalletDetailsLogoTextColor");
      this.zzb(var4, styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType, "maskedWalletDetailsLogoImageType");
      var4.recycle();
   }

   private final void zza(TypedArray var1, int var2, String var3) {
      if (!this.zzbQx.containsKey(var3)) {
         TypedValue var4;
         if ((var4 = var1.peekValue(var2)) != null) {
            Bundle var10000 = this.zzbQx;
            long var10002;
            switch(var4.type) {
            case 5:
               var10002 = zzl(128, var4.data);
               break;
            case 16:
               var10002 = zzbO(var4.data);
               break;
            default:
               int var6 = var4.type;
               throw new IllegalArgumentException((new StringBuilder(38)).append("Unexpected dimension type: ").append(var6).toString());
            }

            var10000.putLong(var3, var10002);
         }

      }
   }

   private final void zzb(TypedArray var1, int var2, String var3) {
      if (!this.zzbQx.containsKey(var3)) {
         TypedValue var4;
         if ((var4 = var1.peekValue(var2)) != null) {
            this.zzbQx.putInt(var3, var4.data);
         }

      }
   }

   private final void zza(TypedArray var1, int var2, String var3, String var4) {
      if (!this.zzbQx.containsKey(var3) && !this.zzbQx.containsKey(var4)) {
         TypedValue var5;
         if ((var5 = var1.peekValue(var2)) != null) {
            if (var5.type >= 28 && var5.type <= 31) {
               this.zzbQx.putInt(var3, var5.data);
               return;
            }

            this.zzbQx.putInt(var4, var5.resourceId);
         }

      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbQx, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbQy);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Dimension {
      int UNIT_PX = 0;
      int UNIT_DIP = 1;
      int UNIT_SP = 2;
      int UNIT_PT = 3;
      int UNIT_IN = 4;
      int UNIT_MM = 5;
      int MATCH_PARENT = -1;
      int WRAP_CONTENT = -2;
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface LogoImageType {
      /** @deprecated */
      @Deprecated
      int GOOGLE_WALLET_CLASSIC = 1;
      /** @deprecated */
      @Deprecated
      int GOOGLE_WALLET_MONOCHROME = 2;
      int ANDROID_PAY = 3;
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface BuyButtonAppearance {
      int ANDROID_PAY_DARK = 4;
      int ANDROID_PAY_LIGHT = 5;
      int ANDROID_PAY_LIGHT_WITH_BORDER = 6;
      /** @deprecated */
      @Deprecated
      int GOOGLE_WALLET_CLASSIC = 1;
      /** @deprecated */
      @Deprecated
      int GOOGLE_WALLET_GRAYSCALE = 2;
      /** @deprecated */
      @Deprecated
      int GOOGLE_WALLET_MONOCHROME = 3;
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface BuyButtonText {
      int BUY_WITH = 5;
      int LOGO_ONLY = 6;
      int DONATE_WITH = 7;
   }
}
