package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public final class zzh implements zzw {
   private final Executor zzr;

   public zzh(Handler var1) {
      this.zzr = new zzi(this, var1);
   }

   public final void zza(zzp var1, zzt var2) {
      this.zza(var1, var2, (Runnable)null);
   }

   public final void zza(zzp var1, zzt var2, Runnable var3) {
      var1.zzk();
      var1.zzb("post-response");
      this.zzr.execute(new zzj(this, var1, var2, var3));
   }

   public final void zza(zzp var1, zzaa var2) {
      var1.zzb("post-error");
      zzt var3 = zzt.zzc(var2);
      this.zzr.execute(new zzj(this, var1, var3, (Runnable)null));
   }
}
