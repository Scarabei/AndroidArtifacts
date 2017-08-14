package com.google.android.gms.internal;

import java.io.InputStream;
import java.util.concurrent.Callable;

final class zzail implements Callable {
   // $FF: synthetic field
   private InputStream zzaam;
   // $FF: synthetic field
   private zzaij zzaan;

   zzail(zzaij var1, InputStream var2) {
      this.zzaan = var1;
      this.zzaam = var2;
      super();
   }

   public final Object call() {
      return zzaij.zza(this.zzaan).zzh(this.zzaam);
   }
}
