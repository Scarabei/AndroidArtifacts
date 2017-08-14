package com.google.android.gms.internal;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public final class zzcua {
   private volatile long zzbDe;
   private volatile long zzbDf;
   private volatile boolean zzbHD;
   private volatile boolean mClosed;
   private volatile Info zzafw;
   private volatile long zzbDg;
   private volatile long zzbDh;
   private final Context mContext;
   private final zze zzvw;
   private final Thread zzYV;
   private final Object zzbDi;
   private zzcud zzbHE;
   private static Object zzbDk = new Object();
   private static zzcua zzbHF;

   public static zzcua zzbu(Context var0) {
      if (zzbHF == null) {
         Object var1 = zzbDk;
         synchronized(zzbDk) {
            if (zzbHF == null) {
               (zzbHF = new zzcua(var0)).zzYV.start();
            }
         }
      }

      return zzbHF;
   }

   private zzcua(Context var1) {
      this(var1, (zzcud)null, zzi.zzrY());
   }

   private zzcua(Context var1, zzcud var2, zze var3) {
      this.zzbDe = 900000L;
      this.zzbDf = 30000L;
      this.zzbHD = true;
      this.mClosed = false;
      this.zzbDi = new Object();
      this.zzbHE = new zzcub(this);
      this.zzvw = var3;
      if (var1 != null) {
         this.mContext = var1.getApplicationContext();
      } else {
         this.mContext = var1;
      }

      this.zzbDg = this.zzvw.currentTimeMillis();
      this.zzYV = new Thread(new zzcuc(this));
   }

   public final String zzAy() {
      if (this.zzafw == null) {
         this.zzAz();
      } else {
         this.zzAA();
      }

      this.zzAB();
      return this.zzafw == null ? null : this.zzafw.getId();
   }

   public final boolean isLimitAdTrackingEnabled() {
      if (this.zzafw == null) {
         this.zzAz();
      } else {
         this.zzAA();
      }

      this.zzAB();
      return this.zzafw == null ? true : this.zzafw.isLimitAdTrackingEnabled();
   }

   private final void zzAz() {
      synchronized(this) {
         try {
            this.zzAA();
            this.wait(500L);
         } catch (InterruptedException var3) {
            ;
         }

      }
   }

   private final void zzAA() {
      if (this.zzvw.currentTimeMillis() - this.zzbDg > this.zzbDf) {
         Object var1 = this.zzbDi;
         synchronized(this.zzbDi) {
            this.zzbDi.notify();
         }

         this.zzbDg = this.zzvw.currentTimeMillis();
      }

   }

   private final void zzAB() {
      if (this.zzvw.currentTimeMillis() - this.zzbDh > 3600000L) {
         this.zzafw = null;
      }

   }

   private final void zzAC() {
      Process.setThreadPriority(10);

      while(true) {
         boolean var10000 = this.mClosed;
         Info var1 = null;
         if (this.zzbHD) {
            var1 = this.zzbHE.zzAD();
         }

         if (var1 != null) {
            this.zzafw = var1;
            this.zzbDh = this.zzvw.currentTimeMillis();
            zzcvk.zzaS("Obtained fresh AdvertisingId info from GmsCore.");
         }

         synchronized(this) {
            this.notifyAll();
         }

         try {
            Object var2 = this.zzbDi;
            synchronized(this.zzbDi) {
               this.zzbDi.wait(this.zzbDe);
            }
         } catch (InterruptedException var6) {
            zzcvk.zzaS("sleep interrupted in AdvertiserDataPoller thread; continuing");
         }
      }
   }

   // $FF: synthetic method
   static Context zza(zzcua var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static boolean zza(zzcua var0, boolean var1) {
      return var0.zzbHD = false;
   }

   // $FF: synthetic method
   static void zzb(zzcua var0) {
      var0.zzAC();
   }
}
