package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public class zziv extends zza {
   public static final Creator CREATOR = new zziw();
   public final String zzAs;
   public final int height;
   public final int heightPixels;
   public final boolean zzAt;
   public final int width;
   public final int widthPixels;
   public final zziv[] zzAu;
   public final boolean zzAv;
   public final boolean zzAw;
   public boolean zzAx;

   public static int zza(DisplayMetrics var0) {
      return var0.widthPixels;
   }

   public static int zzb(DisplayMetrics var0) {
      return (int)((float)zzc(var0) * var0.density);
   }

   private static int zzc(DisplayMetrics var0) {
      int var1;
      if ((var1 = (int)((float)var0.heightPixels / var0.density)) <= 400) {
         return 32;
      } else {
         return var1 <= 720 ? 50 : 90;
      }
   }

   public static zziv zzg(Context var0) {
      return new zziv("320x50_mb", 0, 0, false, 0, 0, (zziv[])null, true, false, false);
   }

   public static zziv zzdk() {
      return new zziv("reward_mb", 0, 0, true, 0, 0, (zziv[])null, false, false, false);
   }

   public zziv() {
      this("interstitial_mb", 0, 0, true, 0, 0, (zziv[])null, false, false, false);
   }

   public zziv(Context var1, AdSize var2) {
      this(var1, new AdSize[]{var2});
   }

   public zziv(Context var1, AdSize[] var2) {
      AdSize var3 = var2[0];
      this.zzAt = false;
      this.zzAw = var3.isFluid();
      if (this.zzAw) {
         this.width = AdSize.BANNER.getWidth();
         this.height = AdSize.BANNER.getHeight();
      } else {
         this.width = var3.getWidth();
         this.height = var3.getHeight();
      }

      boolean var4 = this.width == -1;
      boolean var5 = this.height == -2;
      DisplayMetrics var6 = var1.getResources().getDisplayMetrics();
      int var7;
      if (var4) {
         label62: {
            zzji.zzds();
            if (zzaiy.zzZ(var1)) {
               zzji.zzds();
               if (zzaiy.zzaa(var1)) {
                  int var10001 = var6.widthPixels;
                  zzji.zzds();
                  this.widthPixels = var10001 - zzaiy.zzab(var1);
                  break label62;
               }
            }

            this.widthPixels = var6.widthPixels;
         }

         double var9;
         var7 = (int)(var9 = (double)((float)this.widthPixels / var6.density));
         if (var9 - (double)((int)var9) >= 0.01D) {
            ++var7;
         }
      } else {
         var7 = this.width;
         zzji.zzds();
         this.widthPixels = zzaiy.zza(var6, this.width);
      }

      int var8;
      if (var5) {
         var8 = zzc(var6);
      } else {
         var8 = this.height;
      }

      zzji.zzds();
      this.heightPixels = zzaiy.zza(var6, var8);
      if (!var4 && !var5) {
         if (this.zzAw) {
            this.zzAs = "320x50_mb";
         } else {
            this.zzAs = var3.toString();
         }
      } else {
         this.zzAs = (new StringBuilder(26)).append(var7).append("x").append(var8).append("_as").toString();
      }

      if (var2.length > 1) {
         this.zzAu = new zziv[var2.length];

         for(int var11 = 0; var11 < var2.length; ++var11) {
            this.zzAu[var11] = new zziv(var1, var2[var11]);
         }
      } else {
         this.zzAu = null;
      }

      this.zzAv = false;
      this.zzAx = false;
   }

   public zziv(zziv var1, zziv[] var2) {
      this(var1.zzAs, var1.height, var1.heightPixels, var1.zzAt, var1.width, var1.widthPixels, var2, var1.zzAv, var1.zzAw, var1.zzAx);
   }

   zziv(String var1, int var2, int var3, boolean var4, int var5, int var6, zziv[] var7, boolean var8, boolean var9, boolean var10) {
      this.zzAs = var1;
      this.height = var2;
      this.heightPixels = var3;
      this.zzAt = var4;
      this.width = var5;
      this.widthPixels = var6;
      this.zzAu = var7;
      this.zzAv = var8;
      this.zzAw = var9;
      this.zzAx = var10;
   }

   public final AdSize zzdl() {
      return zzb.zza(this.width, this.height, this.zzAs);
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzAs, false);
      zzd.zzc(var1, 3, this.height);
      zzd.zzc(var1, 4, this.heightPixels);
      zzd.zza(var1, 5, this.zzAt);
      zzd.zzc(var1, 6, this.width);
      zzd.zzc(var1, 7, this.widthPixels);
      zzd.zza(var1, 8, this.zzAu, var2, false);
      zzd.zza(var1, 9, this.zzAv);
      zzd.zza(var1, 10, this.zzAw);
      zzd.zza(var1, 11, this.zzAx);
      zzd.zzI(var1, var5);
   }
}
