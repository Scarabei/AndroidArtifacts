package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzd implements Runnable {
   // $FF: synthetic field
   private Task zzbLT;
   // $FF: synthetic field
   private zzc zzbLV;

   zzd(zzc var1, Task var2) {
      this.zzbLV = var1;
      this.zzbLT = var2;
      super();
   }

   public final void run() {
      Task var1;
      try {
         var1 = (Task)zzc.zza(this.zzbLV).then(this.zzbLT);
      } catch (RuntimeExecutionException var3) {
         if (var3.getCause() instanceof Exception) {
            zzc.zzb(this.zzbLV).setException((Exception)var3.getCause());
            return;
         }

         zzc.zzb(this.zzbLV).setException(var3);
         return;
      } catch (Exception var4) {
         zzc.zzb(this.zzbLV).setException(var4);
         return;
      }

      if (var1 == null) {
         this.zzbLV.onFailure(new NullPointerException("Continuation returned null"));
      } else {
         var1.addOnSuccessListener((Executor)TaskExecutors.zzbMf, this.zzbLV);
         var1.addOnFailureListener((Executor)TaskExecutors.zzbMf, this.zzbLV);
      }
   }
}
