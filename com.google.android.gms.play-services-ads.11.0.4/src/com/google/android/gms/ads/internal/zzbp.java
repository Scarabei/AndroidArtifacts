package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzeu;
import java.util.concurrent.Callable;

final class zzbp implements Callable {
   // $FF: synthetic field
   private zzbm zzvf;

   zzbp(zzbm var1) {
      this.zzvf = var1;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return new zzeu(zzbm.zzc(this.zzvf).zzaP, zzbm.zzd(this.zzvf), false);
   }
}
