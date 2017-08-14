package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

@zzzn
public final class zzafl {
   private long zzYM = -1L;
   private long zzYN = -1L;
   private int zzYO = -1;
   int zzYI = -1;
   private long zzYP = 0L;
   private final Object mLock = new Object();
   private String mSessionId;
   private int zzYQ = 0;
   private int zzYR = 0;

   public zzafl(String var1) {
      this.mSessionId = var1;
   }

   public final void zzhd() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         ++this.zzYQ;
      }
   }

   public final void zzhc() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         ++this.zzYR;
      }
   }

   public final void zzb(zzir var1, long var2) {
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         long var5 = com.google.android.gms.ads.internal.zzbs.zzbD().zzhy();
         long var7 = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis();
         if (this.zzYN == -1L) {
            long var10000 = var7 - var5;
            zzme var10 = zzmo.zzDL;
            if (var10000 > ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var10)).longValue()) {
               this.zzYI = -1;
            } else {
               this.zzYI = com.google.android.gms.ads.internal.zzbs.zzbD().zzhB();
            }

            this.zzYN = var2;
            this.zzYM = this.zzYN;
         } else {
            this.zzYM = var2;
         }

         if (var1.extras == null || var1.extras.getInt("gw", 2) != 1) {
            ++this.zzYO;
            ++this.zzYI;
            if (this.zzYI == 0) {
               this.zzYP = 0L;
               com.google.android.gms.ads.internal.zzbs.zzbD().zzk(var7);
            } else {
               this.zzYP = var7 - com.google.android.gms.ads.internal.zzbs.zzbD().zzhz();
            }

         }
      }
   }

   public final Bundle zzo(Context var1, String var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         Bundle var4;
         (var4 = new Bundle()).putString("session_id", this.mSessionId);
         var4.putLong("basets", this.zzYN);
         var4.putLong("currts", this.zzYM);
         var4.putString("seq_num", var2);
         var4.putInt("preqs", this.zzYO);
         var4.putInt("preqs_in_session", this.zzYI);
         var4.putLong("time_in_session", this.zzYP);
         var4.putInt("pclick", this.zzYQ);
         var4.putInt("pimp", this.zzYR);
         var4.putBoolean("support_transparent_background", zzB(var1));
         return var4;
      }
   }

   private static boolean zzB(Context var0) {
      int var1;
      if ((var1 = var0.getResources().getIdentifier("Theme.Translucent", "style", "android")) == 0) {
         zzafr.zzaS("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
         return false;
      } else {
         ComponentName var2 = new ComponentName(var0.getPackageName(), "com.google.android.gms.ads.AdActivity");

         try {
            int var3 = var0.getPackageManager().getActivityInfo(var2, 0).theme;
            if (var1 == var3) {
               return true;
            } else {
               zzafr.zzaS("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
               return false;
            }
         } catch (NameNotFoundException var4) {
            zzafr.zzaT("Fail to fetch AdActivity theme");
            zzafr.zzaS("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
         }
      }
   }
}
