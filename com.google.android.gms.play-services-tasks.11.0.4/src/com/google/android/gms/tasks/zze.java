package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zze implements zzk {
   private final Executor zzbEo;
   private final Object mLock = new Object();
   private OnCompleteListener zzbLW;

   public zze(@NonNull Executor var1, @NonNull OnCompleteListener var2) {
      this.zzbEo = var1;
      this.zzbLW = var2;
   }

   public final void onComplete(@NonNull Task var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzbLW == null) {
            return;
         }
      }

      this.zzbEo.execute(new zzf(this, var1));
   }

   public final void cancel() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzbLW = null;
      }
   }

   // $FF: synthetic method
   static Object zza(zze var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static OnCompleteListener zzb(zze var0) {
      return var0.zzbLW;
   }
}
