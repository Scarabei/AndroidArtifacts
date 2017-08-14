package com.google.android.gms.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.Callable;

final class zzaij extends zzp {
   private final zzaii zzaaj;
   private final zzv zzaD;

   public zzaij(String var1, zzaii var2, zzv var3) {
      super(0, var1, new zzaik(var3, var2));
      this.zzaaj = var2;
      this.zzaD = var3;
   }

   protected final zzt zza(zzn var1) {
      return zzt.zza(new ByteArrayInputStream(var1.data), zzam.zzb(var1));
   }

   // $FF: synthetic method
   protected final void zza(Object var1) {
      InputStream var3 = (InputStream)var1;
      zzajm var4;
      (var4 = zzagt.zza((Callable)(new zzail(this, var3)))).zzd(new zzaim(this, var4));
   }

   // $FF: synthetic method
   static zzaii zza(zzaij var0) {
      return var0.zzaaj;
   }

   // $FF: synthetic method
   static zzv zzb(zzaij var0) {
      return var0.zzaD;
   }
}
