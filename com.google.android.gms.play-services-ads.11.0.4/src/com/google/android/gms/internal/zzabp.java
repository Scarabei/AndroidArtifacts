package com.google.android.gms.internal;

final class zzabp implements zzajq {
   // $FF: synthetic field
   private zzabn zzUE;

   zzabp(zzabn var1) {
      this.zzUE = var1;
      super();
   }

   public final void run() {
      zzafr.e("JS engine could not be obtained. Cancelling ad request");
      this.zzUE.zzUA.fail();
   }
}
