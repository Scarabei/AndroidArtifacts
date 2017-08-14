package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzc implements OnFailureListener, OnSuccessListener, zzk {
   private final Executor zzbEo;
   private final Continuation zzbLR;
   private final zzn zzbLS;

   public zzc(@NonNull Executor var1, @NonNull Continuation var2, @NonNull zzn var3) {
      this.zzbEo = var1;
      this.zzbLR = var2;
      this.zzbLS = var3;
   }

   public final void onComplete(@NonNull Task var1) {
      this.zzbEo.execute(new zzd(this, var1));
   }

   public final void onSuccess(Object var1) {
      this.zzbLS.setResult(var1);
   }

   public final void onFailure(@NonNull Exception var1) {
      this.zzbLS.setException(var1);
   }

   public final void cancel() {
      throw new UnsupportedOperationException();
   }

   // $FF: synthetic method
   static Continuation zza(zzc var0) {
      return var0.zzbLR;
   }

   // $FF: synthetic method
   static zzn zzb(zzc var0) {
      return var0.zzbLS;
   }
}
