package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzaia;
import com.google.android.gms.internal.zzaic;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzmu;
import com.google.android.gms.internal.zzmz;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzzn;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

@zzzn
public final class zzar {
   private final Context mContext;
   private final String zzQx;
   private final zzaje zzuK;
   @Nullable
   private final zzmz zzQy;
   @Nullable
   private final zznb zzPs;
   private final zzaia zzQz = (new zzaid()).zza("min_1", Double.MIN_VALUE, 1.0D).zza("1_5", 1.0D, 5.0D).zza("5_10", 5.0D, 10.0D).zza("10_20", 10.0D, 20.0D).zza("20_30", 20.0D, 30.0D).zza("30_max", 30.0D, Double.MAX_VALUE).zzid();
   private final long[] zzQA;
   private final String[] zzQB;
   private boolean zzQC = false;
   private boolean zzQD = false;
   private boolean zzQE = false;
   private boolean zzQF = false;
   private boolean zzPw;
   private zzy zzQG;
   private boolean zzQH;
   private boolean zzQI;
   private long zzQJ = -1L;

   public zzar(Context var1, zzaje var2, String var3, @Nullable zznb var4, @Nullable zzmz var5) {
      this.mContext = var1;
      this.zzuK = var2;
      this.zzQx = var3;
      this.zzPs = var4;
      this.zzQy = var5;
      zzme var10 = zzmo.zzCu;
      String var6;
      if ((var6 = (String)zzbs.zzbL().zzd(var10)) == null) {
         this.zzQB = new String[0];
         this.zzQA = new long[0];
      } else {
         String[] var7 = TextUtils.split(var6, ",");
         this.zzQB = new String[var7.length];
         this.zzQA = new long[var7.length];

         for(int var8 = 0; var8 < var7.length; ++var8) {
            try {
               this.zzQA[var8] = Long.parseLong(var7[var8]);
            } catch (NumberFormatException var11) {
               zzafr.zzc("Unable to parse frame hash target time number.", var11);
               this.zzQA[var8] = -1L;
            }
         }

      }
   }

   public final void zza(zzy var1) {
      zzmu.zza(this.zzPs, this.zzQy, "vpc2");
      this.zzQC = true;
      if (this.zzPs != null) {
         this.zzPs.zzh("vpn", var1.zzfD());
      }

      this.zzQG = var1;
   }

   public final void zzfT() {
      if (this.zzQC && !this.zzQD) {
         zzmu.zza(this.zzPs, this.zzQy, "vfr2");
         this.zzQD = true;
      }
   }

   public final void onStop() {
      zzme var6 = zzmo.zzCt;
      if (((Boolean)zzbs.zzbL().zzd(var6)).booleanValue() && !this.zzQH) {
         Bundle var1;
         (var1 = new Bundle()).putString("type", "native-player-metrics");
         var1.putString("request", this.zzQx);
         var1.putString("player", this.zzQG.zzfD());

         String var10001;
         zzaic var3;
         for(Iterator var2 = this.zzQz.getBuckets().iterator(); var2.hasNext(); var1.putString(var10001, Double.toString(var3.zzZZ))) {
            var3 = (zzaic)var2.next();
            var10001 = String.valueOf("fps_c_");
            String var10002 = String.valueOf(var3.name);
            String var10003;
            if (var10002.length() != 0) {
               var10001 = var10001.concat(var10002);
            } else {
               var10003 = new String;
               var10002 = var10001;
               var10001 = var10003;
               var10003.<init>(var10002);
            }

            var1.putString(var10001, Integer.toString(var3.count));
            var10001 = String.valueOf("fps_p_");
            var10002 = String.valueOf(var3.name);
            if (var10002.length() != 0) {
               var10001 = var10001.concat(var10002);
            } else {
               var10003 = new String;
               var10002 = var10001;
               var10001 = var10003;
               var10003.<init>(var10002);
            }
         }

         for(int var7 = 0; var7 < this.zzQA.length; ++var7) {
            String var8;
            if ((var8 = this.zzQB[var7]) != null) {
               String var4 = String.valueOf("fh_");
               String var5 = String.valueOf(this.zzQA[var7]);
               var1.putString((new StringBuilder(String.valueOf(var4).length() + String.valueOf(var5).length())).append(var4).append(var5).toString(), var8);
            }
         }

         zzbs.zzbz().zza(this.mContext, this.zzuK.zzaP, "gmob-apps", var1, true);
         this.zzQH = true;
      }

   }

   public final void zzb(zzy var1) {
      if (this.zzQE && !this.zzQF) {
         zzmu.zza(this.zzPs, this.zzQy, "vff2");
         this.zzQF = true;
      }

      long var3 = zzbs.zzbF().nanoTime();
      if (this.zzPw && this.zzQI && this.zzQJ != -1L) {
         double var5 = (double)TimeUnit.SECONDS.toNanos(1L) / (double)(var3 - this.zzQJ);
         this.zzQz.zza(var5);
      }

      this.zzQI = this.zzPw;
      this.zzQJ = var3;
      zzar var2 = this;
      zzme var11 = zzmo.zzCv;
      long var4 = ((Long)zzbs.zzbL().zzd(var11)).longValue();
      long var6 = (long)var1.getCurrentPosition();

      for(int var8 = 0; var8 < var2.zzQB.length; ++var8) {
         if (var2.zzQB[var8] == null) {
            long var9 = var2.zzQA[var8];
            if (var4 > Math.abs(var6 - var9)) {
               String[] var10000 = var2.zzQB;
               Bitmap var12 = var1.getBitmap(8, 8);
               long var13 = 0L;
               long var15 = 63L;

               for(int var17 = 0; var17 < 8; ++var17) {
                  for(int var18 = 0; var18 < 8; --var15) {
                     int var19;
                     long var20 = Color.blue(var19 = var12.getPixel(var18, var17)) + Color.red(var19) + Color.green(var19) > 128 ? 1L : 0L;
                     var13 |= var20 << (int)var15;
                     ++var18;
                  }
               }

               var10000[var8] = String.format("%016X", var13);
               return;
            }
         }
      }

   }

   public final void zzgj() {
      this.zzPw = true;
      if (this.zzQD && !this.zzQE) {
         zzmu.zza(this.zzPs, this.zzQy, "vfp2");
         this.zzQE = true;
      }

   }

   public final void zzgk() {
      this.zzPw = false;
   }
}
