package com.google.android.gms.internal;

import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzao;
import com.google.android.gms.common.internal.zzbo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzbbe extends PendingResult {
   static final ThreadLocal zzaBV = new zzbbf();
   private final Object zzaBW = new Object();
   private zzbbg zzaBX;
   private WeakReference zzaBY;
   private final CountDownLatch zztJ = new CountDownLatch(1);
   private final ArrayList zzaBZ = new ArrayList();
   private ResultCallback zzaCa;
   private final AtomicReference zzaCb = new AtomicReference();
   private Result zzaBj;
   private Status mStatus;
   private zzbbh zzaCc;
   private volatile boolean zzaCd;
   private boolean zzJ;
   private boolean zzaCe;
   private zzao zzaCf;
   private volatile zzbes zzaCg;
   private boolean zzaCh = false;

   /** @deprecated */
   @Deprecated
   zzbbe() {
      this.zzaBX = new zzbbg(Looper.getMainLooper());
      this.zzaBY = new WeakReference((Object)null);
   }

   protected zzbbe(GoogleApiClient var1) {
      Looper var2 = var1 != null ? var1.getLooper() : Looper.getMainLooper();
      this.zzaBX = new zzbbg(var2);
      this.zzaBY = new WeakReference(var1);
   }

   /** @deprecated */
   @Deprecated
   protected zzbbe(Looper var1) {
      this.zzaBX = new zzbbg(var1);
      this.zzaBY = new WeakReference((Object)null);
   }

   public final boolean isReady() {
      return this.zztJ.getCount() == 0L;
   }

   public final Result await() {
      zzbo.zza(Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread");
      zzbo.zza(!this.zzaCd, "Result has already been consumed");
      zzbo.zza(this.zzaCg == null, "Cannot await if then() has been called.");

      try {
         this.zztJ.await();
      } catch (InterruptedException var1) {
         this.zzs(Status.zzaBn);
      }

      zzbo.zza(this.isReady(), "Result is not ready.");
      return this.get();
   }

   public final Result await(long var1, TimeUnit var3) {
      zzbo.zza(var1 <= 0L || Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread when time is greater than zero.");
      zzbo.zza(!this.zzaCd, "Result has already been consumed.");
      zzbo.zza(this.zzaCg == null, "Cannot await if then() has been called.");

      try {
         if (!this.zztJ.await(var1, var3)) {
            this.zzs(Status.zzaBp);
         }
      } catch (InterruptedException var4) {
         this.zzs(Status.zzaBn);
      }

      zzbo.zza(this.isReady(), "Result is not ready.");
      return this.get();
   }

   public final void setResultCallback(ResultCallback var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         if (var1 == null) {
            this.zzaCa = null;
         } else {
            zzbo.zza(!this.zzaCd, "Result has already been consumed.");
            zzbo.zza(this.zzaCg == null, "Cannot set callbacks if then() has been called.");
            if (!this.isCanceled()) {
               if (this.isReady()) {
                  this.zzaBX.zza(var1, this.get());
               } else {
                  this.zzaCa = var1;
               }

            }
         }
      }
   }

   public final void setResultCallback(ResultCallback var1, long var2, TimeUnit var4) {
      Object var5 = this.zzaBW;
      synchronized(this.zzaBW) {
         if (var1 == null) {
            this.zzaCa = null;
         } else {
            zzbo.zza(!this.zzaCd, "Result has already been consumed.");
            zzbo.zza(this.zzaCg == null, "Cannot set callbacks if then() has been called.");
            if (!this.isCanceled()) {
               if (this.isReady()) {
                  this.zzaBX.zza(var1, this.get());
               } else {
                  this.zzaCa = var1;
                  zzbbg var10000 = this.zzaBX;
                  long var9 = var4.toMillis(var2);
                  var10000.sendMessageDelayed(var10000.obtainMessage(2, this), var9);
               }

            }
         }
      }
   }

   public final void zza(PendingResult.zza var1) {
      zzbo.zzb(var1 != null, "Callback cannot be null.");
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         if (this.isReady()) {
            var1.zzo(this.mStatus);
         } else {
            this.zzaBZ.add(var1);
         }

      }
   }

   public void cancel() {
      Object var1 = this.zzaBW;
      synchronized(this.zzaBW) {
         if (!this.zzJ && !this.zzaCd) {
            if (this.zzaCf != null) {
               try {
                  this.zzaCf.cancel();
               } catch (RemoteException var3) {
                  ;
               }
            }

            zzc(this.zzaBj);
            this.zzJ = true;
            this.zzb(this.zzb(Status.zzaBq));
         }
      }
   }

   public final boolean zzpB() {
      Object var1 = this.zzaBW;
      synchronized(this.zzaBW) {
         if ((GoogleApiClient)this.zzaBY.get() == null || !this.zzaCh) {
            this.cancel();
         }

         return this.isCanceled();
      }
   }

   public boolean isCanceled() {
      Object var1 = this.zzaBW;
      synchronized(this.zzaBW) {
         return this.zzJ;
      }
   }

   public TransformedResult then(ResultTransform var1) {
      zzbo.zza(!this.zzaCd, "Result has already been consumed.");
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         zzbo.zza(this.zzaCg == null, "Cannot call then() twice.");
         zzbo.zza(this.zzaCa == null, "Cannot call then() if callbacks are set.");
         zzbo.zza(!this.zzJ, "Cannot call then() if result was canceled.");
         this.zzaCh = true;
         this.zzaCg = new zzbes(this.zzaBY);
         TransformedResult var3 = this.zzaCg.then(var1);
         if (this.isReady()) {
            this.zzaBX.zza(this.zzaCg, this.get());
         } else {
            this.zzaCa = this.zzaCg;
         }

         return var3;
      }
   }

   public final void setResult(Result var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         if (!this.zzaCe && !this.zzJ) {
            if (this.isReady()) {
               ;
            }

            zzbo.zza(!this.isReady(), "Results have already been set");
            zzbo.zza(!this.zzaCd, "Result has already been consumed");
            this.zzb(var1);
         } else {
            zzc(var1);
         }
      }
   }

   public final void zzs(Status var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         if (!this.isReady()) {
            this.setResult(this.zzb(var1));
            this.zzaCe = true;
         }

      }
   }

   @NonNull
   protected abstract Result zzb(Status var1);

   public final void zza(zzbex var1) {
      this.zzaCb.set(var1);
   }

   public final Integer zzpo() {
      return null;
   }

   protected final void zza(zzao var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         this.zzaCf = var1;
      }
   }

   public final void zzpC() {
      this.zzaCh = this.zzaCh || ((Boolean)zzaBV.get()).booleanValue();
   }

   private final Result get() {
      Object var2 = this.zzaBW;
      Result var1;
      synchronized(this.zzaBW) {
         zzbo.zza(!this.zzaCd, "Result has already been consumed.");
         zzbo.zza(this.isReady(), "Result is not ready.");
         var1 = this.zzaBj;
         this.zzaBj = null;
         this.zzaCa = null;
         this.zzaCd = true;
      }

      zzbex var5;
      if ((var5 = (zzbex)this.zzaCb.getAndSet((Object)null)) != null) {
         var5.zzc(this);
      }

      return var1;
   }

   private final void zzb(Result var1) {
      this.zzaBj = var1;
      this.zzaCf = null;
      this.zztJ.countDown();
      this.mStatus = this.zzaBj.getStatus();
      if (this.zzJ) {
         this.zzaCa = null;
      } else if (this.zzaCa == null) {
         if (this.zzaBj instanceof Releasable) {
            this.zzaCc = new zzbbh(this, (zzbbf)null);
         }
      } else {
         this.zzaBX.removeMessages(2);
         this.zzaBX.zza(this.zzaCa, this.get());
      }

      ArrayList var2;
      int var3 = (var2 = (ArrayList)this.zzaBZ).size();
      int var4 = 0;

      while(var4 < var3) {
         Object var10000 = var2.get(var4);
         ++var4;
         ((PendingResult.zza)var10000).zzo(this.mStatus);
      }

      this.zzaBZ.clear();
   }

   public static void zzc(Result var0) {
      if (var0 instanceof Releasable) {
         try {
            ((Releasable)var0).release();
            return;
         } catch (RuntimeException var3) {
            String var2 = String.valueOf(var0);
            Log.w("BasePendingResult", (new StringBuilder(18 + String.valueOf(var2).length())).append("Unable to release ").append(var2).toString(), var3);
         }
      }

   }

   // $FF: synthetic method
   static Result zza(zzbbe var0) {
      return var0.zzaBj;
   }
}
