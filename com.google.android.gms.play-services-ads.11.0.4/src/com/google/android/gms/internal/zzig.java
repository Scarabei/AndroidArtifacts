package com.google.android.gms.internal;

import android.os.Handler;
import java.util.HashMap;

@zzzn
public final class zzig {
   private HashMap zzzA;
   private final zzij zzzB;
   private zzil zzzC;
   private boolean zzzD = false;
   private final boolean zzzE;
   private final int zzzF;
   private int zzzG = 0;
   private zzih zzzH;

   public static zzig zzde() {
      return new zzig();
   }

   public zzig(zzij var1, boolean var2) {
      this.zzzB = var1;
      this.zzzA = new HashMap();
      this.zzzE = var2;
      zzme var3 = zzmo.zzGE;
      this.zzzF = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).intValue();
      this.zzzC = new zzil();
      this.zzdi();
      com.google.android.gms.ads.internal.zzbs.zzbP().zzie();
   }

   private zzig() {
      this.zzzE = false;
      this.zzzB = new zzik();
      this.zzzC = new zzil();
      this.zzzF = 0;
      this.zzdi();
   }

   public final void zzdf() {
      if (this.zzzE) {
         this.zzzD = true;
         this.zzdh();
      }

   }

   private final synchronized void zzdg() {
   }

   public final synchronized void zza(zzii var1) {
      if (this.zzzE) {
         var1.zza(this.zzzC);
      }

      if (this.zzzD && this.zzzE) {
         this.zzdh();
      }

   }

   private final synchronized void zzdh() {
      Handler var1 = com.google.android.gms.ads.internal.zzbs.zzbP().getHandler();
      zzih var2 = new zzih(this, this.zzzG + 1);
      var1.postDelayed(var2, (long)this.zzzF);
      ++this.zzzG;
      if (this.zzzH != null) {
         var1.removeCallbacks(this.zzzH);
      }

      this.zzzH = var2;
   }

   private final synchronized void zzdi() {
   }

   // $FF: synthetic method
   static int zza(zzig var0) {
      return var0.zzzG;
   }

   // $FF: synthetic method
   static void zzb(zzig var0) {
      var0.zzdg();
   }
}
