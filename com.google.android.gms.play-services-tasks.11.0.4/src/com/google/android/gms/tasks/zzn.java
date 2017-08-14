package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbds;
import com.google.android.gms.internal.zzbdt;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

final class zzn extends Task {
   private final Object mLock = new Object();
   private final zzl zzbMg = new zzl();
   private boolean zzbMh;
   private Object zzbMi;
   private Exception zzbMj;

   public final boolean isComplete() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzbMh;
      }
   }

   public final boolean isSuccessful() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzbMh && this.zzbMj == null;
      }
   }

   public final Object getResult() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzDG();
         if (this.zzbMj != null) {
            throw new RuntimeExecutionException(this.zzbMj);
         } else {
            return this.zzbMi;
         }
      }
   }

   public final Object getResult(@NonNull Class var1) throws Throwable {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzDG();
         if (var1.isInstance(this.zzbMj)) {
            throw (Throwable)var1.cast(this.zzbMj);
         } else if (this.zzbMj != null) {
            throw new RuntimeExecutionException(this.zzbMj);
         } else {
            return this.zzbMi;
         }
      }
   }

   @Nullable
   public final Exception getException() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzbMj;
      }
   }

   @NonNull
   public final Task addOnSuccessListener(@NonNull OnSuccessListener var1) {
      return this.addOnSuccessListener(TaskExecutors.MAIN_THREAD, var1);
   }

   @NonNull
   public final Task addOnSuccessListener(@NonNull Executor var1, @NonNull OnSuccessListener var2) {
      this.zzbMg.zza((zzk)(new zzi(var1, var2)));
      this.zzDI();
      return this;
   }

   @NonNull
   public final Task addOnSuccessListener(@NonNull Activity var1, @NonNull OnSuccessListener var2) {
      zzi var3 = new zzi(TaskExecutors.MAIN_THREAD, var2);
      this.zzbMg.zza((zzk)var3);
      zzn.zza.zzr(var1).zzb(var3);
      this.zzDI();
      return this;
   }

   @NonNull
   public final Task addOnFailureListener(@NonNull OnFailureListener var1) {
      return this.addOnFailureListener(TaskExecutors.MAIN_THREAD, var1);
   }

   @NonNull
   public final Task addOnFailureListener(@NonNull Executor var1, @NonNull OnFailureListener var2) {
      this.zzbMg.zza((zzk)(new zzg(var1, var2)));
      this.zzDI();
      return this;
   }

   @NonNull
   public final Task addOnFailureListener(@NonNull Activity var1, @NonNull OnFailureListener var2) {
      zzg var3 = new zzg(TaskExecutors.MAIN_THREAD, var2);
      this.zzbMg.zza((zzk)var3);
      zzn.zza.zzr(var1).zzb(var3);
      this.zzDI();
      return this;
   }

   @NonNull
   public final Task addOnCompleteListener(@NonNull OnCompleteListener var1) {
      return this.addOnCompleteListener(TaskExecutors.MAIN_THREAD, var1);
   }

   @NonNull
   public final Task addOnCompleteListener(@NonNull Executor var1, @NonNull OnCompleteListener var2) {
      this.zzbMg.zza((zzk)(new zze(var1, var2)));
      this.zzDI();
      return this;
   }

   @NonNull
   public final Task addOnCompleteListener(@NonNull Activity var1, @NonNull OnCompleteListener var2) {
      zze var3 = new zze(TaskExecutors.MAIN_THREAD, var2);
      this.zzbMg.zza((zzk)var3);
      zzn.zza.zzr(var1).zzb(var3);
      this.zzDI();
      return this;
   }

   @NonNull
   public final Task continueWith(@NonNull Continuation var1) {
      return this.continueWith(TaskExecutors.MAIN_THREAD, var1);
   }

   @NonNull
   public final Task continueWith(@NonNull Executor var1, @NonNull Continuation var2) {
      zzn var3 = new zzn();
      this.zzbMg.zza((zzk)(new zza(var1, var2, var3)));
      this.zzDI();
      return var3;
   }

   @NonNull
   public final Task continueWithTask(@NonNull Continuation var1) {
      return this.continueWithTask(TaskExecutors.MAIN_THREAD, var1);
   }

   @NonNull
   public final Task continueWithTask(@NonNull Executor var1, @NonNull Continuation var2) {
      zzn var3 = new zzn();
      this.zzbMg.zza((zzk)(new zzc(var1, var2, var3)));
      this.zzDI();
      return var3;
   }

   public final void setResult(Object var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzDH();
         this.zzbMh = true;
         this.zzbMi = var1;
      }

      this.zzbMg.zza((Task)this);
   }

   public final boolean trySetResult(Object var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzbMh) {
            return false;
         }

         this.zzbMh = true;
         this.zzbMi = var1;
      }

      this.zzbMg.zza((Task)this);
      return true;
   }

   public final void setException(@NonNull Exception var1) {
      zzbo.zzb(var1, "Exception must not be null");
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzDH();
         this.zzbMh = true;
         this.zzbMj = var1;
      }

      this.zzbMg.zza((Task)this);
   }

   public final boolean trySetException(@NonNull Exception var1) {
      zzbo.zzb(var1, "Exception must not be null");
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzbMh) {
            return false;
         }

         this.zzbMh = true;
         this.zzbMj = var1;
      }

      this.zzbMg.zza((Task)this);
      return true;
   }

   private final void zzDG() {
      zzbo.zza(this.zzbMh, "Task is not yet complete");
   }

   private final void zzDH() {
      zzbo.zza(!this.zzbMh, "Task is already complete");
   }

   private final void zzDI() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzbMh) {
            return;
         }
      }

      this.zzbMg.zza((Task)this);
   }

   static class zza extends zzbds {
      private final List mListeners = new ArrayList();

      public static zzn.zza zzr(Activity var0) {
         zzbdt var1;
         zzn.zza var2;
         if ((var2 = (zzn.zza)(var1 = zzn(var0)).zza("TaskOnStopCallback", zzn.zza.class)) == null) {
            var2 = new zzn.zza(var1);
         }

         return var2;
      }

      private zza(zzbdt var1) {
         super(var1);
         this.zzaEG.zza("TaskOnStopCallback", this);
      }

      public final void zzb(zzk var1) {
         List var2 = this.mListeners;
         synchronized(this.mListeners) {
            this.mListeners.add(new WeakReference(var1));
         }
      }

      @MainThread
      public final void onStop() {
         List var1 = this.mListeners;
         synchronized(this.mListeners) {
            Iterator var2 = this.mListeners.iterator();

            while(var2.hasNext()) {
               zzk var3;
               if ((var3 = (zzk)((WeakReference)var2.next()).get()) != null) {
                  var3.cancel();
               }
            }

            this.mListeners.clear();
         }
      }
   }
}
