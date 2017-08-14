package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zza implements zzk {
   private final Executor zzbEo;
   private final Continuation zzbLR;
   private final zzn zzbLS;

   public zza(@NonNull Executor var1, @NonNull Continuation var2, @NonNull zzn var3) {
      this.zzbEo = var1;
      this.zzbLR = var2;
      this.zzbLS = var3;
   }

   public final void onComplete(@NonNull Task var1) {
      this.zzbEo.execute(new zzb(this, var1));
   }

   public final void cancel() {
      throw new UnsupportedOperationException();
   }

   // $FF: synthetic method
   static Continuation zza(zza var0) {
      return var0.zzbLR;
   }

   // $FF: synthetic method
   static zzn zzb(zza var0) {
      return var0.zzbLS;
   }
}
