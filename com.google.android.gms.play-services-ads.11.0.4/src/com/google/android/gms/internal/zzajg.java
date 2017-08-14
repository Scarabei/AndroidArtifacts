package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzzn
public class zzajg implements zzajm {
   private final Object mLock = new Object();
   private Object mValue;
   private Throwable zzaaS;
   private boolean zzaaT;
   private boolean zzMP;
   private final zzajn zzaaU = new zzajn();

   public final void zzc(Runnable var1) {
      this.zzaaU.zzc(var1);
   }

   public final void zzd(Runnable var1) {
      this.zzaaU.zzd(var1);
   }

   public final void zzg(@Nullable Object var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzMP) {
            if (this.zzim()) {
               com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)(new IllegalStateException("Provided CallbackFuture with multiple values.")), (String)"CallbackFuture.provideValue");
            } else {
               this.zzaaT = true;
               this.mValue = var1;
               this.mLock.notifyAll();
               this.zzaaU.zzin();
            }
         }
      }
   }

   public final void zzb(Throwable var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzMP) {
            if (this.zzim()) {
               com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)(new IllegalStateException("Provided CallbackFuture with multiple values.")), (String)"CallbackFuture.provideException");
            } else {
               this.zzaaS = var1;
               this.mLock.notifyAll();
               this.zzaaU.zzin();
            }
         }
      }
   }

   public Object get() throws CancellationException, ExecutionException, InterruptedException {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzim()) {
            try {
               this.mLock.wait();
            } catch (InterruptedException var3) {
               throw var3;
            }
         }

         if (this.zzaaS != null) {
            throw new ExecutionException(this.zzaaS);
         } else if (this.zzMP) {
            throw new CancellationException("CallbackFuture was cancelled.");
         } else {
            return this.mValue;
         }
      }
   }

   public Object get(long var1, TimeUnit var3) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzim()) {
            try {
               long var5;
               if ((var5 = var3.toMillis(var1)) != 0L) {
                  this.mLock.wait(var5);
               }
            } catch (InterruptedException var8) {
               throw var8;
            }
         }

         if (this.zzaaS != null) {
            throw new ExecutionException(this.zzaaS);
         } else if (!this.zzaaT) {
            throw new TimeoutException("CallbackFuture timed out.");
         } else if (this.zzMP) {
            throw new CancellationException("CallbackFuture was cancelled.");
         } else {
            return this.mValue;
         }
      }
   }

   public boolean cancel(boolean var1) {
      if (!var1) {
         return false;
      } else {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            if (this.zzim()) {
               return false;
            } else {
               this.zzMP = true;
               this.zzaaT = true;
               this.mLock.notifyAll();
               this.zzaaU.zzin();
               return true;
            }
         }
      }
   }

   public boolean isCancelled() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzMP;
      }
   }

   public boolean isDone() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzim();
      }
   }

   private final boolean zzim() {
      return this.zzaaS != null || this.zzaaT;
   }
}
