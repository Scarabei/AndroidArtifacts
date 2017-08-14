package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {
   public static Task forResult(Object var0) {
      zzn var1;
      (var1 = new zzn()).setResult(var0);
      return var1;
   }

   public static Task forException(@NonNull Exception var0) {
      zzn var1;
      (var1 = new zzn()).setException(var0);
      return var1;
   }

   public static Task call(@NonNull Callable var0) {
      return call(TaskExecutors.MAIN_THREAD, var0);
   }

   public static Task call(@NonNull Executor var0, @NonNull Callable var1) {
      zzbo.zzb(var0, "Executor must not be null");
      zzbo.zzb(var1, "Callback must not be null");
      zzn var2 = new zzn();
      var0.execute(new zzo(var2, var1));
      return var2;
   }

   public static Object await(@NonNull Task var0) throws ExecutionException, InterruptedException {
      zzbo.zzcG("Must not be called on the main application thread");
      zzbo.zzb(var0, "Task must not be null");
      if (var0.isComplete()) {
         return zzb(var0);
      } else {
         Tasks.zza var1 = new Tasks.zza((zzo)null);
         zza(var0, var1);
         var1.await();
         return zzb(var0);
      }
   }

   public static Object await(@NonNull Task var0, long var1, @NonNull TimeUnit var3) throws ExecutionException, InterruptedException, TimeoutException {
      zzbo.zzcG("Must not be called on the main application thread");
      zzbo.zzb(var0, "Task must not be null");
      zzbo.zzb(var3, "TimeUnit must not be null");
      if (var0.isComplete()) {
         return zzb(var0);
      } else {
         Tasks.zza var4 = new Tasks.zza((zzo)null);
         zza(var0, var4);
         if (!var4.await(var1, var3)) {
            throw new TimeoutException("Timed out waiting for Task");
         } else {
            return zzb(var0);
         }
      }
   }

   public static Task whenAll(Collection var0) {
      if (var0.isEmpty()) {
         return forResult((Object)null);
      } else {
         Iterator var1 = var0.iterator();

         while(var1.hasNext()) {
            if ((Task)var1.next() == null) {
               throw new NullPointerException("null tasks are not accepted");
            }
         }

         zzn var4 = new zzn();
         Tasks.zzc var2 = new Tasks.zzc(var0.size(), var4);
         Iterator var3 = var0.iterator();

         while(var3.hasNext()) {
            zza((Task)var3.next(), var2);
         }

         return var4;
      }
   }

   public static Task whenAll(Task... var0) {
      return var0.length == 0 ? forResult((Object)null) : whenAll((Collection)Arrays.asList(var0));
   }

   private static Object zzb(Task var0) throws ExecutionException {
      if (var0.isSuccessful()) {
         return var0.getResult();
      } else {
         throw new ExecutionException(var0.getException());
      }
   }

   private static void zza(Task var0, Tasks.zzb var1) {
      var0.addOnSuccessListener((Executor)TaskExecutors.zzbMf, var1);
      var0.addOnFailureListener((Executor)TaskExecutors.zzbMf, var1);
   }

   static final class zzc implements Tasks.zzb {
      private final Object mLock = new Object();
      private final int zzbMl;
      private final zzn zzbMe;
      private int zzbMm;
      private int zzbMn;
      private Exception zzbMj;

      public zzc(int var1, zzn var2) {
         this.zzbMl = var1;
         this.zzbMe = var2;
      }

      public final void onFailure(@NonNull Exception var1) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            ++this.zzbMn;
            this.zzbMj = var1;
            this.zzDJ();
         }
      }

      public final void onSuccess(Object var1) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            ++this.zzbMm;
            this.zzDJ();
         }
      }

      private final void zzDJ() {
         if (this.zzbMm + this.zzbMn == this.zzbMl) {
            if (this.zzbMj == null) {
               this.zzbMe.setResult((Object)null);
               return;
            }

            int var1 = this.zzbMn;
            int var2 = this.zzbMl;
            this.zzbMe.setException(new ExecutionException((new StringBuilder(54)).append(var1).append(" out of ").append(var2).append(" underlying tasks failed").toString(), this.zzbMj));
         }

      }
   }

   static final class zza implements Tasks.zzb {
      private final CountDownLatch zztJ;

      private zza() {
         this.zztJ = new CountDownLatch(1);
      }

      public final void onSuccess(Object var1) {
         this.zztJ.countDown();
      }

      public final void onFailure(@NonNull Exception var1) {
         this.zztJ.countDown();
      }

      public final void await() throws InterruptedException {
         this.zztJ.await();
      }

      public final boolean await(long var1, TimeUnit var3) throws InterruptedException {
         return this.zztJ.await(var1, var3);
      }

      // $FF: synthetic method
      zza(zzo var1) {
         this();
      }
   }

   interface zzb extends OnFailureListener, OnSuccessListener {
   }
}
