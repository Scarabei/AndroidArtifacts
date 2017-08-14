package com.google.android.gms.tasks;

final class zzb implements Runnable {
   // $FF: synthetic field
   private Task zzbLT;
   // $FF: synthetic field
   private zza zzbLU;

   zzb(zza var1, Task var2) {
      this.zzbLU = var1;
      this.zzbLT = var2;
      super();
   }

   public final void run() {
      Object var1;
      try {
         var1 = zza.zza(this.zzbLU).then(this.zzbLT);
      } catch (RuntimeExecutionException var3) {
         if (var3.getCause() instanceof Exception) {
            zza.zzb(this.zzbLU).setException((Exception)var3.getCause());
            return;
         }

         zza.zzb(this.zzbLU).setException(var3);
         return;
      } catch (Exception var4) {
         zza.zzb(this.zzbLU).setException(var4);
         return;
      }

      zza.zzb(this.zzbLU).setResult(var1);
   }
}
