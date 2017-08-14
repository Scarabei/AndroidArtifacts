package com.google.android.gms.internal;

import android.content.Context;

final class zzabq implements Runnable {
   // $FF: synthetic field
   private zzabl zzUF;
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzabu zzUA;
   // $FF: synthetic field
   private zzaae zzUG;

   zzabq(zzabl var1, Context var2, zzabu var3, zzaae var4) {
      this.zzUF = var1;
      this.zztF = var2;
      this.zzUA = var3;
      this.zzUG = var4;
      super();
   }

   public final void run() {
      this.zzUF.zzUr.zza(this.zztF, this.zzUA, this.zzUG.zzvT);
   }
}
