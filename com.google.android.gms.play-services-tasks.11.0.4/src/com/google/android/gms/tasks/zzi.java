package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzi implements zzk {
   private final Executor zzbEo;
   private final Object mLock = new Object();
   private OnSuccessListener zzbMa;

   public zzi(@NonNull Executor var1, @NonNull OnSuccessListener var2) {
      this.zzbEo = var1;
      this.zzbMa = var2;
   }

   public final void onComplete(@NonNull Task var1) {
      if (var1.isSuccessful()) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            if (this.zzbMa == null) {
               return;
            }
         }

         this.zzbEo.execute(new zzj(this, var1));
      }

   }

   public final void cancel() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzbMa = null;
      }
   }

   // $FF: synthetic method
   static Object zza(zzi var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static OnSuccessListener zzb(zzi var0) {
      return var0.zzbMa;
   }
}
