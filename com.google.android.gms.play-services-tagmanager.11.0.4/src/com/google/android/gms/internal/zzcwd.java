package com.google.android.gms.internal;

import android.content.Context;
import android.content.IntentFilter;

final class zzcwd extends zzcwc {
   private static final Object zzbGC = new Object();
   private Context zzbGD;
   private zzcvb zzbIT;
   private volatile zzcuy zzbIU;
   private int zzbGG = 1800000;
   private boolean zzbGH = true;
   private boolean zzbGI = false;
   private boolean zzbIV = false;
   private boolean connected = true;
   private boolean zzbGJ = true;
   private zzcvc zzbIW = new zzcwe(this);
   private zzcwg zzbIX;
   private zzcvm zzbIY;
   private boolean zzbGN = false;
   private static zzcwd zzbIZ;

   public static zzcwd zzCA() {
      if (zzbIZ == null) {
         zzbIZ = new zzcwd();
      }

      return zzbIZ;
   }

   final synchronized void zza(Context var1, zzcuy var2) {
      if (this.zzbGD == null) {
         this.zzbGD = var1.getApplicationContext();
         if (this.zzbIU == null) {
            this.zzbIU = var2;
         }

      }
   }

   final synchronized zzcvb zzCB() {
      if (this.zzbIT == null) {
         if (this.zzbGD == null) {
            throw new IllegalStateException("Cant get a store unless we have a context");
         }

         this.zzbIT = new zzcvn(this.zzbIW, this.zzbGD);
      }

      if (this.zzbIX == null) {
         this.zzbIX = new zzcwh(this, (zzcwe)null);
         if (this.zzbGG > 0) {
            this.zzbIX.zzs((long)this.zzbGG);
         }
      }

      this.zzbGI = true;
      if (this.zzbGH) {
         this.dispatch();
         this.zzbGH = false;
      }

      if (this.zzbIY == null && this.zzbGJ) {
         this.zzbIY = new zzcvm(this);
         Context var3 = this.zzbGD;
         zzcvm var2 = this.zzbIY;
         IntentFilter var4;
         (var4 = new IntentFilter()).addAction("android.net.conn.CONNECTIVITY_CHANGE");
         var3.registerReceiver(var2, var4);
         IntentFilter var5;
         (var5 = new IntentFilter()).addAction("com.google.analytics.RADIO_POWERED");
         var5.addCategory(var3.getPackageName());
         var3.registerReceiver(var2, var5);
      }

      return this.zzbIT;
   }

   public final synchronized void dispatch() {
      if (!this.zzbGI) {
         zzcvk.v("Dispatch call queued. Dispatch will run once initialization is complete.");
         this.zzbGH = true;
      } else {
         if (!this.zzbIV) {
            this.zzbIV = true;
            this.zzbIU.zzn(new zzcwf(this));
         }

      }
   }

   final synchronized void zzd(boolean var1, boolean var2) {
      boolean var3 = this.isPowerSaveMode();
      this.zzbGN = var1;
      this.connected = var2;
      if (this.isPowerSaveMode() != var3) {
         if (this.isPowerSaveMode()) {
            this.zzbIX.cancel();
            zzcvk.v("PowerSaveMode initiated.");
         } else {
            this.zzbIX.zzs((long)this.zzbGG);
            zzcvk.v("PowerSaveMode terminated.");
         }
      }
   }

   public final synchronized void zzas(boolean var1) {
      this.zzd(this.zzbGN, var1);
   }

   public final synchronized void zzBU() {
      if (!this.isPowerSaveMode()) {
         this.zzbIX.zzBY();
      }

   }

   private final boolean isPowerSaveMode() {
      return this.zzbGN || !this.connected || this.zzbGG <= 0;
   }

   // $FF: synthetic method
   static Context zza(zzcwd var0) {
      return var0.zzbGD;
   }

   // $FF: synthetic method
   static Object zzBX() {
      return zzbGC;
   }

   // $FF: synthetic method
   static boolean zzb(zzcwd var0) {
      return var0.isPowerSaveMode();
   }

   // $FF: synthetic method
   static int zzc(zzcwd var0) {
      return var0.zzbGG;
   }

   // $FF: synthetic method
   static boolean zzd(zzcwd var0) {
      return var0.connected;
   }

   // $FF: synthetic method
   static boolean zza(zzcwd var0, boolean var1) {
      return var0.zzbIV = false;
   }

   // $FF: synthetic method
   static zzcvb zze(zzcwd var0) {
      return var0.zzbIT;
   }
}
