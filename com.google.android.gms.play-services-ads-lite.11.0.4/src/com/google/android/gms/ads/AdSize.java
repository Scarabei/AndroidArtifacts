package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzji;

public final class AdSize {
   public static final int FULL_WIDTH = -1;
   public static final int AUTO_HEIGHT = -2;
   public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
   public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
   public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
   public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
   public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
   public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
   public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
   public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
   public static final AdSize zzrV = new AdSize(50, 50, "50x50_mb");
   public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
   private final int zzrW;
   private final int zzrX;
   private final String zzrY;

   public AdSize(int var1, int var2) {
      String var3 = var1 == -1 ? "FULL" : String.valueOf(var1);
      String var4 = var2 == -2 ? "AUTO" : String.valueOf(var2);
      String var5 = String.valueOf("_as");
      this(var1, var2, (new StringBuilder(1 + String.valueOf(var3).length() + String.valueOf(var4).length() + String.valueOf(var5).length())).append(var3).append("x").append(var4).append(var5).toString());
   }

   AdSize(int var1, int var2, String var3) {
      if (var1 < 0 && var1 != -1 && var1 != -3) {
         throw new IllegalArgumentException((new StringBuilder(37)).append("Invalid width for AdSize: ").append(var1).toString());
      } else if (var2 < 0 && var2 != -2 && var2 != -4) {
         throw new IllegalArgumentException((new StringBuilder(38)).append("Invalid height for AdSize: ").append(var2).toString());
      } else {
         this.zzrW = var1;
         this.zzrX = var2;
         this.zzrY = var3;
      }
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof AdSize)) {
         return false;
      } else {
         AdSize var2 = (AdSize)var1;
         return this.zzrW == var2.zzrW && this.zzrX == var2.zzrX && this.zzrY.equals(var2.zzrY);
      }
   }

   public final int getHeight() {
      return this.zzrX;
   }

   public final int getHeightInPixels(Context var1) {
      switch(this.zzrX) {
      case -4:
      case -3:
         return -1;
      case -2:
         return zziv.zzb(var1.getResources().getDisplayMetrics());
      default:
         zzji.zzds();
         return zzaiy.zzc(var1, this.zzrX);
      }
   }

   public final int getWidth() {
      return this.zzrW;
   }

   public final int getWidthInPixels(Context var1) {
      switch(this.zzrW) {
      case -4:
      case -3:
         return -1;
      case -2:
      default:
         zzji.zzds();
         return zzaiy.zzc(var1, this.zzrW);
      case -1:
         return zziv.zza(var1.getResources().getDisplayMetrics());
      }
   }

   public final int hashCode() {
      return this.zzrY.hashCode();
   }

   public final boolean isAutoHeight() {
      return this.zzrX == -2;
   }

   public final boolean isFullWidth() {
      return this.zzrW == -1;
   }

   public final boolean isFluid() {
      return this.zzrW == -3 && this.zzrX == -4;
   }

   public final String toString() {
      return this.zzrY;
   }
}
