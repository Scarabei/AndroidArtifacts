package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class VideoConfiguration extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   public static final int QUALITY_LEVEL_FULLHD = 3;
   public static final int QUALITY_LEVEL_XHD = 2;
   public static final int QUALITY_LEVEL_HD = 1;
   public static final int QUALITY_LEVEL_SD = 0;
   public static final int QUALITY_LEVEL_UNKNOWN = -1;
   public static final int NUM_QUALITY_LEVEL = 4;
   public static final int CAPTURE_MODE_UNKNOWN = -1;
   public static final int CAPTURE_MODE_FILE = 0;
   public static final int CAPTURE_MODE_STREAM = 1;
   public static final int NUM_CAPTURE_MODE = 2;
   private final int zzbfi;
   private final int zzbfb;
   private final String zzbfj;
   private final String zzbfk;
   private final String zzbfl;
   private final String zzbfm;
   private final boolean zzbfn;

   public VideoConfiguration(int var1, int var2, String var3, String var4, String var5, String var6, boolean var7) {
      zzbo.zzaf(isValidQualityLevel(var1, false));
      zzbo.zzaf(isValidCaptureMode(var2, false));
      this.zzbfi = var1;
      this.zzbfb = var2;
      this.zzbfn = var7;
      if (var2 == 1) {
         this.zzbfk = var4;
         this.zzbfj = var3;
         this.zzbfl = var5;
         this.zzbfm = var6;
      } else {
         zzbo.zzb(var4 == null, "Stream key should be null when not streaming");
         zzbo.zzb(var3 == null, "Stream url should be null when not streaming");
         zzbo.zzb(var5 == null, "Stream title should be null when not streaming");
         zzbo.zzb(var6 == null, "Stream description should be null when not streaming");
         this.zzbfk = null;
         this.zzbfj = null;
         this.zzbfl = null;
         this.zzbfm = null;
      }
   }

   public final int getQualityLevel() {
      return this.zzbfi;
   }

   public final int getCaptureMode() {
      return this.zzbfb;
   }

   public final String getStreamUrl() {
      return this.zzbfj;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.getQualityLevel());
      zzd.zzc(var1, 2, this.getCaptureMode());
      zzd.zza(var1, 3, this.getStreamUrl(), false);
      zzd.zza(var1, 4, this.zzbfk, false);
      zzd.zza(var1, 5, this.zzbfl, false);
      zzd.zza(var1, 6, this.zzbfm, false);
      zzd.zza(var1, 7, this.zzbfn);
      zzd.zzI(var1, var5);
   }

   public static boolean isValidQualityLevel(int var0, boolean var1) {
      switch(var0) {
      case -1:
         return var1;
      case 0:
      case 1:
      case 2:
      case 3:
         return true;
      default:
         return false;
      }
   }

   public static boolean isValidCaptureMode(int var0, boolean var1) {
      switch(var0) {
      case -1:
         return var1;
      case 0:
      case 1:
         return true;
      default:
         return false;
      }
   }

   public static final class Builder {
      private int zzbfi;
      private int zzbfb;
      private boolean zzbfn;
      private String zzbfj;
      private String zzbfk;
      private String zzbfl;
      private String zzbfm;

      public Builder(int var1, int var2) {
         this.zzbfi = var1;
         this.zzbfb = var2;
         this.zzbfn = true;
         this.zzbfj = null;
         this.zzbfk = null;
         this.zzbfl = null;
         this.zzbfm = null;
      }

      public final VideoConfiguration.Builder setQualityLevel(int var1) {
         this.zzbfi = var1;
         return this;
      }

      public final VideoConfiguration.Builder setCaptureMode(int var1) {
         this.zzbfb = var1;
         return this;
      }

      public final VideoConfiguration build() {
         return new VideoConfiguration(this.zzbfi, this.zzbfb, (String)null, (String)null, (String)null, (String)null, this.zzbfn);
      }
   }
}
