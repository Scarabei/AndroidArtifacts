package com.google.android.gms.ads.formats;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class NativeAdOptions {
   public static final int ORIENTATION_ANY = 0;
   public static final int ORIENTATION_PORTRAIT = 1;
   public static final int ORIENTATION_LANDSCAPE = 2;
   public static final int ADCHOICES_TOP_LEFT = 0;
   public static final int ADCHOICES_TOP_RIGHT = 1;
   public static final int ADCHOICES_BOTTOM_RIGHT = 2;
   public static final int ADCHOICES_BOTTOM_LEFT = 3;
   private final boolean zzsn;
   private final int zzso;
   private final boolean zzsp;
   private final int zzsq;
   private final VideoOptions zzsr;

   private NativeAdOptions(NativeAdOptions.Builder var1) {
      this.zzsn = var1.zzsn;
      this.zzso = var1.zzso;
      this.zzsp = var1.zzsp;
      this.zzsq = var1.zzsq;
      this.zzsr = var1.zzsr;
   }

   public final boolean shouldReturnUrlsForImageAssets() {
      return this.zzsn;
   }

   public final int getImageOrientation() {
      return this.zzso;
   }

   public final boolean shouldRequestMultipleImages() {
      return this.zzsp;
   }

   public final int getAdChoicesPlacement() {
      return this.zzsq;
   }

   @Nullable
   public final VideoOptions getVideoOptions() {
      return this.zzsr;
   }

   // $FF: synthetic method
   NativeAdOptions(NativeAdOptions.Builder var1, zza var2) {
      this(var1);
   }

   public static final class Builder {
      private boolean zzsn = false;
      private int zzso = -1;
      private boolean zzsp = false;
      private VideoOptions zzsr;
      private int zzsq = 1;

      public final NativeAdOptions.Builder setReturnUrlsForImageAssets(boolean var1) {
         this.zzsn = var1;
         return this;
      }

      public final NativeAdOptions.Builder setImageOrientation(int var1) {
         this.zzso = var1;
         return this;
      }

      public final NativeAdOptions.Builder setRequestMultipleImages(boolean var1) {
         this.zzsp = var1;
         return this;
      }

      public final NativeAdOptions.Builder setAdChoicesPlacement(@NativeAdOptions.AdChoicesPlacement int var1) {
         this.zzsq = var1;
         return this;
      }

      public final NativeAdOptions.Builder setVideoOptions(VideoOptions var1) {
         this.zzsr = var1;
         return this;
      }

      public final NativeAdOptions build() {
         return new NativeAdOptions(this, (zza)null);
      }
   }

   public @interface AdChoicesPlacement {
   }
}
