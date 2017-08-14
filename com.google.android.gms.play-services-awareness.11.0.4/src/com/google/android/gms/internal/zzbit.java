package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.awareness.fence.zza;

public final class zzbit extends zzbjm {
   public static final zzet zzaKT = new zzbiu();
   private final zza zzaKU;
   private final Handler mHandler;

   /** @deprecated */
   @Deprecated
   public final void zza(zzbjh var1) {
      zzeq.zza("ContextFenceListenerWrapper", "Unexpected call to deprecated method onFenceTriggered() with %s.", (Object)var1);
      this.mHandler.post(new zzbiv(this.zzaKU, new zzbjd(var1.zzaLi ? 2 : 1, 0L, var1.key, 0)));
   }

   public final void zza(zzbjd var1) {
      this.mHandler.post(new zzbiv(this.zzaKU, var1));
   }
}
