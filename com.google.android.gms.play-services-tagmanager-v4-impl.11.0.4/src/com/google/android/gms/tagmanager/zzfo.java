package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.IntentFilter;

final class zzfo extends zzfn {
   private static final Object zzbGC = new Object();
   private Context zzbGD;
   private zzcc zzbGE;
   private volatile zzbz zzbGF;
   private int zzbGG = 1800000;
   private boolean zzbGH = true;
   private boolean zzbGI = false;
   private boolean connected = true;
   private boolean zzbGJ = true;
   private zzcd zzbGK = new zzfp(this);
   private zzfr zzbGL;
   private zzdo zzbGM;
   private boolean zzbGN = false;
   private static zzfo zzbGO;

   public static zzfo zzBV() {
      if (zzbGO == null) {
         zzbGO = new zzfo();
      }

      return zzbGO;
   }

   final synchronized void zza(Context var1, zzbz var2) {
      if (this.zzbGD == null) {
         this.zzbGD = var1.getApplicationContext();
         if (this.zzbGF == null) {
            this.zzbGF = var2;
         }

      }
   }

   final synchronized zzcc zzBW() {
      if (this.zzbGE == null) {
         if (this.zzbGD == null) {
            throw new IllegalStateException("Cant get a store unless we have a context");
         }

         this.zzbGE = new zzec(this.zzbGK, this.zzbGD);
      }

      if (this.zzbGL == null) {
         this.zzbGL = new zzfs(this, (zzfp)null);
         if (this.zzbGG > 0) {
            this.zzbGL.zzs((long)this.zzbGG);
         }
      }

      this.zzbGI = true;
      if (this.zzbGH) {
         this.dispatch();
         this.zzbGH = false;
      }

      if (this.zzbGM == null && this.zzbGJ) {
         this.zzbGM = new zzdo(this);
         Context var3 = this.zzbGD;
         zzdo var2 = this.zzbGM;
         IntentFilter var4;
         (var4 = new IntentFilter()).addAction("android.net.conn.CONNECTIVITY_CHANGE");
         var3.registerReceiver(var2, var4);
         IntentFilter var5;
         (var5 = new IntentFilter()).addAction("com.google.analytics.RADIO_POWERED");
         var5.addCategory(var3.getPackageName());
         var3.registerReceiver(var2, var5);
      }

      return this.zzbGE;
   }

   public final synchronized void dispatch() {
      if (!this.zzbGI) {
         zzdj.v("Dispatch call queued. Dispatch will run once initialization is complete.");
         this.zzbGH = true;
      } else {
         this.zzbGF.zzn(new zzfq(this));
      }
   }

   final synchronized void zzd(boolean var1, boolean var2) {
      boolean var3 = this.isPowerSaveMode();
      this.zzbGN = var1;
      this.connected = var2;
      if (this.isPowerSaveMode() != var3) {
         if (this.isPowerSaveMode()) {
            this.zzbGL.cancel();
            zzdj.v("PowerSaveMode initiated.");
         } else {
            this.zzbGL.zzs((long)this.zzbGG);
            zzdj.v("PowerSaveMode terminated.");
         }
      }
   }

   public final synchronized void zzas(boolean var1) {
      this.zzd(this.zzbGN, var1);
   }

   public final synchronized void zzBU() {
      if (!this.isPowerSaveMode()) {
         this.zzbGL.zzBY();
      }

   }

   private final boolean isPowerSaveMode() {
      return this.zzbGN || !this.connected || this.zzbGG <= 0;
   }

   // $FF: synthetic method
   static Context zza(zzfo var0) {
      return var0.zzbGD;
   }

   // $FF: synthetic method
   static Object zzBX() {
      return zzbGC;
   }

   // $FF: synthetic method
   static boolean zzb(zzfo var0) {
      return var0.isPowerSaveMode();
   }

   // $FF: synthetic method
   static int zzc(zzfo var0) {
      return var0.zzbGG;
   }

   // $FF: synthetic method
   static boolean zzd(zzfo var0) {
      return var0.connected;
   }

   // $FF: synthetic method
   static zzcc zze(zzfo var0) {
      return var0.zzbGE;
   }
}
