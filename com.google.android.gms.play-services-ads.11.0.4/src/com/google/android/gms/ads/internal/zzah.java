package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagt;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzcp;
import com.google.android.gms.internal.zzct;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzzn;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzzn
final class zzah implements zzcp, Runnable {
   private final List zztH = new Vector();
   private final AtomicReference zztI = new AtomicReference();
   private zzbt zzsP;
   private CountDownLatch zztJ = new CountDownLatch(1);

   public zzah(zzbt var1) {
      this.zzsP = var1;
      zzji.zzds();
      if (zzaiy.zzil()) {
         zzagt.zza((Runnable)this);
      } else {
         this.run();
      }
   }

   private final boolean zzaU() {
      try {
         this.zztJ.await();
         return true;
      } catch (InterruptedException var2) {
         zzafr.zzc("Interrupted during GADSignals creation.", var2);
         return false;
      }
   }

   private final void zzaV() {
      if (!this.zztH.isEmpty()) {
         Iterator var1 = this.zztH.iterator();

         while(var1.hasNext()) {
            Object[] var2;
            if ((var2 = (Object[])var1.next()).length == 1) {
               ((zzcp)this.zztI.get()).zza((MotionEvent)var2[0]);
            } else if (var2.length == 3) {
               ((zzcp)this.zztI.get()).zza(((Integer)var2[0]).intValue(), ((Integer)var2[1]).intValue(), ((Integer)var2[2]).intValue());
            }
         }

         this.zztH.clear();
      }
   }

   private static Context zze(Context var0) {
      zzme var2 = zzmo.zzCi;
      if (!((Boolean)zzbs.zzbL().zzd(var2)).booleanValue()) {
         return var0;
      } else {
         Context var1;
         return (var1 = var0.getApplicationContext()) == null ? var0 : var1;
      }
   }

   public final String zza(Context var1) {
      zzcp var4;
      if (this.zzaU() && (var4 = (zzcp)this.zztI.get()) != null) {
         this.zzaV();
         return var4.zza(zze(var1));
      } else {
         return "";
      }
   }

   public final String zza(Context var1, String var2, View var3) {
      zzcp var4;
      if (this.zzaU() && (var4 = (zzcp)this.zztI.get()) != null) {
         this.zzaV();
         return var4.zza(zze(var1), var2, var3);
      } else {
         return "";
      }
   }

   public final void zza(MotionEvent var1) {
      zzcp var2;
      if ((var2 = (zzcp)this.zztI.get()) != null) {
         this.zzaV();
         var2.zza(var1);
      } else {
         this.zztH.add(new Object[]{var1});
      }
   }

   public final void zza(int var1, int var2, int var3) {
      zzcp var4;
      if ((var4 = (zzcp)this.zztI.get()) != null) {
         this.zzaV();
         var4.zza(var1, var2, var3);
      } else {
         this.zztH.add(new Object[]{var1, var2, var3});
      }
   }

   public final void run() {
      try {
         boolean var1 = false;
         boolean var2 = false;
         if (this.zzsP.zzvT.zzaaQ) {
            var1 = true;
         }

         zzme var4 = zzmo.zzDO;
         if (!((Boolean)zzbs.zzbL().zzd(var4)).booleanValue() && var1) {
            var2 = true;
         }

         zzct var5 = zzct.zza(this.zzsP.zzvT.zzaP, zze(this.zzsP.zzqD), var2);
         this.zztI.set(var5);
      } finally {
         this.zztJ.countDown();
         this.zzsP = null;
      }

   }
}
