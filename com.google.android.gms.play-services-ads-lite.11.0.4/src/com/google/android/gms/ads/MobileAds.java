package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzlh;

public class MobileAds {
   @RequiresPermission("android.permission.INTERNET")
   public static void initialize(Context var0, String var1) {
      initialize(var0, var1, (MobileAds.Settings)null);
   }

   /** @deprecated */
   @Deprecated
   @RequiresPermission("android.permission.INTERNET")
   public static void initialize(Context var0, String var1, MobileAds.Settings var2) {
      zzlf.zzdD().zza(var0, var1, var2 == null ? null : var2.zzad());
   }

   public static void initialize(Context var0) {
      initialize(var0, (String)null, (MobileAds.Settings)null);
   }

   public static RewardedVideoAd getRewardedVideoAdInstance(Context var0) {
      return zzlf.zzdD().getRewardedVideoAdInstance(var0);
   }

   public static void setAppVolume(float var0) {
      zzlf.zzdD().setAppVolume(var0);
   }

   public static void setAppMuted(boolean var0) {
      zzlf.zzdD().setAppMuted(var0);
   }

   public static void openDebugMenu(Context var0, String var1) {
      zzlf.zzdD().openDebugMenu(var0, var1);
   }

   public static final class Settings {
      private final zzlh zzsc = new zzlh();

      /** @deprecated */
      @Deprecated
      public final MobileAds.Settings setTrackingId(String var1) {
         return this;
      }

      /** @deprecated */
      @Deprecated
      public final MobileAds.Settings setGoogleAnalyticsEnabled(boolean var1) {
         return this;
      }

      /** @deprecated */
      @Deprecated
      public final String getTrackingId() {
         return null;
      }

      /** @deprecated */
      @Deprecated
      public final boolean isGoogleAnalyticsEnabled() {
         return false;
      }

      final zzlh zzad() {
         return this.zzsc;
      }
   }
}
