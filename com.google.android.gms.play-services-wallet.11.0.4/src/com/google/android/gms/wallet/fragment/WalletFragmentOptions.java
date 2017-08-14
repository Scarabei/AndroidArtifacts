package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.ReflectedParcelable;

public final class WalletFragmentOptions extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzf();
   private int zzbPT;
   private int mTheme;
   private WalletFragmentStyle zzbQv;
   private int zzaLU;

   public static WalletFragmentOptions.Builder newBuilder() {
      return new WalletFragmentOptions().new Builder((zze)null);
   }

   private WalletFragmentOptions() {
      this.zzbPT = 3;
      this.zzbQv = new WalletFragmentStyle();
   }

   WalletFragmentOptions(int var1, int var2, WalletFragmentStyle var3, int var4) {
      this.zzbPT = var1;
      this.mTheme = var2;
      this.zzbQv = var3;
      this.zzaLU = var4;
   }

   public final int getEnvironment() {
      return this.zzbPT;
   }

   public final int getTheme() {
      return this.mTheme;
   }

   public final WalletFragmentStyle getFragmentStyle() {
      return this.zzbQv;
   }

   public final int getMode() {
      return this.zzaLU;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getEnvironment());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getTheme());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getFragmentStyle(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getMode());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final void zzby(Context var1) {
      if (this.zzbQv != null) {
         this.zzbQv.zzby(var1);
      }

   }

   public static WalletFragmentOptions zza(Context var0, AttributeSet var1) {
      TypedArray var2;
      int var3 = (var2 = var0.obtainStyledAttributes(var1, styleable.WalletFragmentOptions)).getInt(styleable.WalletFragmentOptions_appTheme, 0);
      int var4 = var2.getInt(styleable.WalletFragmentOptions_environment, 1);
      int var5 = var2.getResourceId(styleable.WalletFragmentOptions_fragmentStyle, 0);
      int var6 = var2.getInt(styleable.WalletFragmentOptions_fragmentMode, 1);
      var2.recycle();
      WalletFragmentOptions var7;
      (var7 = new WalletFragmentOptions()).mTheme = var3;
      var7.zzbPT = var4;
      var7.zzbQv = (new WalletFragmentStyle()).setStyleResourceId(var5);
      var7.zzbQv.zzby(var0);
      var7.zzaLU = var6;
      return var7;
   }

   public final class Builder {
      // $FF: synthetic field
      private WalletFragmentOptions zzbQw;

      private Builder() {
         this.zzbQw = WalletFragmentOptions.this;
         super();
      }

      public final WalletFragmentOptions.Builder setEnvironment(int var1) {
         this.zzbQw.zzbPT = var1;
         return this;
      }

      public final WalletFragmentOptions.Builder setTheme(int var1) {
         this.zzbQw.mTheme = var1;
         return this;
      }

      public final WalletFragmentOptions.Builder setFragmentStyle(int var1) {
         this.zzbQw.zzbQv = (new WalletFragmentStyle()).setStyleResourceId(var1);
         return this;
      }

      public final WalletFragmentOptions.Builder setFragmentStyle(WalletFragmentStyle var1) {
         this.zzbQw.zzbQv = var1;
         return this;
      }

      public final WalletFragmentOptions.Builder setMode(int var1) {
         this.zzbQw.zzaLU = var1;
         return this;
      }

      public final WalletFragmentOptions build() {
         return this.zzbQw;
      }

      // $FF: synthetic method
      Builder(zze var2) {
         this();
      }
   }
}
