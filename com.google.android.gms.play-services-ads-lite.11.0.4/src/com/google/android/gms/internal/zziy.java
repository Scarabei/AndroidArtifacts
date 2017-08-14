package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.ads.AdSize;

@zzzn
public final class zziy {
   private final AdSize[] zzAy;
   private final String zztV;

   private static AdSize[] zzC(String var0) {
      String[] var1;
      AdSize[] var2 = new AdSize[(var1 = var0.split("\\s*,\\s*")).length];

      IllegalArgumentException var10000;
      String var10002;
      String var10003;
      String var10004;
      for(int var3 = 0; var3 < var1.length; ++var3) {
         String var4;
         if ((var4 = var1[var3].trim()).matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$")) {
            String[] var5;
            (var5 = var4.split("[xX]"))[0] = var5[0].trim();
            var5[1] = var5[1].trim();

            int var6;
            int var7;
            try {
               var6 = "FULL_WIDTH".equals(var5[0]) ? -1 : Integer.parseInt(var5[0]);
               var7 = "AUTO_HEIGHT".equals(var5[1]) ? -2 : Integer.parseInt(var5[1]);
            } catch (NumberFormatException var8) {
               var10000 = new IllegalArgumentException;
               var10003 = String.valueOf(var4);
               if (var10003.length() != 0) {
                  var10002 = "Could not parse XML attribute \"adSize\": ".concat(var10003);
               } else {
                  var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("Could not parse XML attribute \"adSize\": ");
               }

               var10000.<init>(var10002);
               throw var10000;
            }

            var2[var3] = new AdSize(var6, var7);
         } else if ("BANNER".equals(var4)) {
            var2[var3] = AdSize.BANNER;
         } else if ("LARGE_BANNER".equals(var4)) {
            var2[var3] = AdSize.LARGE_BANNER;
         } else if ("FULL_BANNER".equals(var4)) {
            var2[var3] = AdSize.FULL_BANNER;
         } else if ("LEADERBOARD".equals(var4)) {
            var2[var3] = AdSize.LEADERBOARD;
         } else if ("MEDIUM_RECTANGLE".equals(var4)) {
            var2[var3] = AdSize.MEDIUM_RECTANGLE;
         } else if ("SMART_BANNER".equals(var4)) {
            var2[var3] = AdSize.SMART_BANNER;
         } else if ("WIDE_SKYSCRAPER".equals(var4)) {
            var2[var3] = AdSize.WIDE_SKYSCRAPER;
         } else if ("FLUID".equals(var4)) {
            var2[var3] = AdSize.FLUID;
         } else {
            if (!"ICON".equals(var4)) {
               var10000 = new IllegalArgumentException;
               var10003 = String.valueOf(var4);
               if (var10003.length() != 0) {
                  var10002 = "Could not parse XML attribute \"adSize\": ".concat(var10003);
               } else {
                  var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("Could not parse XML attribute \"adSize\": ");
               }

               var10000.<init>(var10002);
               throw var10000;
            }

            var2[var3] = AdSize.zzrV;
         }
      }

      if (var2.length == 0) {
         var10000 = new IllegalArgumentException;
         var10003 = String.valueOf(var0);
         if (var10003.length() != 0) {
            var10002 = "Could not parse XML attribute \"adSize\": ".concat(var10003);
         } else {
            var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Could not parse XML attribute \"adSize\": ");
         }

         var10000.<init>(var10002);
         throw var10000;
      } else {
         return var2;
      }
   }

   public zziy(Context var1, AttributeSet var2) {
      TypedArray var3;
      String var4 = (var3 = var1.getResources().obtainAttributes(var2, styleable.AdsAttrs)).getString(styleable.AdsAttrs_adSize);
      String var5 = var3.getString(styleable.AdsAttrs_adSizes);
      boolean var6 = !TextUtils.isEmpty(var4);
      boolean var7 = !TextUtils.isEmpty(var5);
      if (var6 && !var7) {
         this.zzAy = zzC(var4);
      } else {
         if (var6 || !var7) {
            if (var6) {
               throw new IllegalArgumentException("Either XML attribute \"adSize\" or XML attribute \"supportedAdSizes\" should be specified, but not both.");
            }

            throw new IllegalArgumentException("Required XML attribute \"adSize\" was missing.");
         }

         this.zzAy = zzC(var5);
      }

      this.zztV = var3.getString(styleable.AdsAttrs_adUnitId);
      if (TextUtils.isEmpty(this.zztV)) {
         throw new IllegalArgumentException("Required XML attribute \"adUnitId\" was missing.");
      }
   }

   public final AdSize[] zzg(boolean var1) {
      if (!var1 && this.zzAy.length != 1) {
         throw new IllegalArgumentException("The adSizes XML attribute is only allowed on PublisherAdViews.");
      } else {
         return this.zzAy;
      }
   }

   public final String getAdUnitId() {
      return this.zztV;
   }
}
