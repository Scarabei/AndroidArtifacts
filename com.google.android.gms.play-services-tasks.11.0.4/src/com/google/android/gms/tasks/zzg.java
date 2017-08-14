package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzg implements zzk {
   private final Executor zzbEo;
   private final Object mLock = new Object();
   private OnFailureListener zzbLY;

   public zzg(@NonNull Executor var1, @NonNull OnFailureListener var2) {
      this.zzbEo = var1;
      this.zzbLY = var2;
   }

   public final void onComplete(@NonNull Task var1) {
      if (!var1.isSuccessful()) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            if (this.zzbLY == null) {
               return;
            }
         }

         this.zzbEo.execute(new zzh(this, var1));
      }

   }

   public final void cancel() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzbLY = null;
      }
   }

   // $FF: synthetic method
   static Object zza(zzg var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static OnFailureListener zzb(zzg var0) {
      return var0.zzbLY;
   }
}
