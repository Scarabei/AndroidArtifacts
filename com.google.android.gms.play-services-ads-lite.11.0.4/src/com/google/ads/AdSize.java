package com.google.ads;

import android.content.Context;

/** @deprecated */
@Deprecated
public final class AdSize {
   private final com.google.android.gms.ads.AdSize zzaG;
   public static final int FULL_WIDTH = -1;
   public static final int AUTO_HEIGHT = -2;
   public static final int LANDSCAPE_AD_HEIGHT = 32;
   public static final int PORTRAIT_AD_HEIGHT = 50;
   public static final int LARGE_AD_HEIGHT = 90;
   public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
   public static final AdSize BANNER = new AdSize(320, 50, "mb");
   public static final AdSize IAB_MRECT = new AdSize(300, 250, "as");
   public static final AdSize IAB_BANNER = new AdSize(468, 60, "as");
   public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, "as");
   public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");

   public AdSize(com.google.android.gms.ads.AdSize var1) {
      this.zzaG = var1;
   }

   public AdSize(int var1, int var2) {
      this(new com.google.android.gms.ads.AdSize(var1, var2));
   }

   private AdSize(int var1, int var2, String var3) {
      this(new com.google.android.gms.ads.AdSize(var1, var2));
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof AdSize)) {
         return false;
      } else {
         AdSize var2 = (AdSize)var1;
         return this.zzaG.equals(var2.zzaG);
      }
   }

   public final int hashCode() {
      return this.zzaG.hashCode();
   }

   public final int getWidth() {
      return this.zzaG.getWidth();
   }

   public final int getHeight() {
      return this.zzaG.getHeight();
   }

   public final boolean isFullWidth() {
      return this.zzaG.isFullWidth();
   }

   public final boolean isAutoHeight() {
      return this.zzaG.isAutoHeight();
   }

   public final boolean isCustomAdSize() {
      return false;
   }

   public final String toString() {
      return this.zzaG.toString();
   }

   public final int getWidthInPixels(Context var1) {
      return this.zzaG.getWidthInPixels(var1);
   }

   public final int getHeightInPixels(Context var1) {
      return this.zzaG.getHeightInPixels(var1);
   }

   public final boolean isSizeAppropriate(int var1, int var2) {
      int var3 = this.getWidth();
      int var4 = this.getHeight();
      return (float)var1 <= (float)var3 * 1.25F && (float)var1 >= (float)var3 * 0.8F && (float)var2 <= (float)var4 * 1.25F && (float)var2 >= (float)var4 * 0.8F;
   }

   public final AdSize findBestSize(AdSize... var1) {
      if (var1 == null) {
         return null;
      } else {
         AdSize var2 = null;
         float var3 = 0.0F;
         int var4 = this.getWidth();
         int var5 = this.getHeight();
         AdSize[] var6 = var1;
         int var7 = var1.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            AdSize var9;
            int var10 = (var9 = var6[var8]).getWidth();
            int var11 = var9.getHeight();
            if (this.isSizeAppropriate(var10, var11)) {
               float var12;
               if ((var12 = (float)(var10 * var11) / (float)(var4 * var5)) > 1.0F) {
                  var12 = 1.0F / var12;
               }

               if (var12 > var3) {
                  var2 = var9;
                  var3 = var12;
               }
            }
         }

         return var2;
      }
   }
}
