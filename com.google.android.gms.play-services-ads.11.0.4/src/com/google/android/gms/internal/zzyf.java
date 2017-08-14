package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbb;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzzn
public final class zzyf extends zzafp {
   private final zzxy zzQP;
   private final zzaai zzQR;
   private final zzafg zzQQ;
   private final zzyn zzRg;
   private final Object mLock;
   private Future zzRh;

   public zzyf(Context var1, zzbb var2, zzafg var3, zzcu var4, zzxy var5, zznb var6) {
      this(var3, var5, new zzyn(var1, var2, new zzaie(var1), var4, var3, var6));
   }

   private zzyf(zzafg var1, zzxy var2, zzyn var3) {
      this.mLock = new Object();
      this.zzQQ = var1;
      this.zzQR = var1.zzXY;
      this.zzQP = var2;
      this.zzRg = var3;
   }

   public final void zzbd() {
      byte var1 = -2;
      zzaff var2 = null;

      try {
         Object var3 = this.mLock;
         synchronized(this.mLock) {
            this.zzRh = zzagt.zza((Callable)this.zzRg);
         }

         var2 = (zzaff)this.zzRh.get(60000L, TimeUnit.MILLISECONDS);
      } catch (TimeoutException var8) {
         zzafr.zzaT("Timed out waiting for native ad.");
         var1 = 2;
         this.zzRh.cancel(true);
      } catch (ExecutionException var9) {
         var1 = 0;
      } catch (InterruptedException var10) {
         var1 = 0;
      } catch (CancellationException var11) {
         var1 = 0;
      }

      zzaff var12 = var2 != null ? var2 : new zzaff(this.zzQQ.zzUj.zzSz, (zzaka)null, (List)null, var1, (List)null, (List)null, this.zzQR.orientation, this.zzQR.zzMg, this.zzQQ.zzUj.zzSC, false, (zzua)null, (zzut)null, (String)null, (zzub)null, (zzud)null, this.zzQR.zzTp, this.zzQQ.zzvX, this.zzQR.zzTn, this.zzQQ.zzXR, this.zzQR.zzTs, this.zzQR.zzTt, this.zzQQ.zzXL, (zzoa)null, (zzaee)null, (List)null, (List)null, this.zzQQ.zzXY.zzTG, this.zzQQ.zzXY.zzTH, (String)null, (List)null, this.zzQR.zzTK, this.zzQQ.zzXX);
      zzagz.zzZr.post(new zzyg(this, var12));
   }

   public final void onStop() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzRh != null) {
            this.zzRh.cancel(true);
         }

      }
   }

   // $FF: synthetic method
   static zzxy zza(zzyf var0) {
      return var0.zzQP;
   }
}
