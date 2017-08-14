package com.google.android.gms.internal;

public final class zzamu extends zzamh {
   private final zzalk zzaeh = new zzalk();

   zzamu(zzamj var1) {
      super(var1);
   }

   protected final void zzjD() {
      this.zzkt().zzjA().zza(this.zzaeh);
      zzaot var2;
      String var3;
      if ((var3 = (var2 = this.zzkx()).zzjG()) != null) {
         this.zzaeh.setAppName(var3);
      }

      String var4;
      if ((var4 = var2.zzjH()) != null) {
         this.zzaeh.setAppVersion(var4);
      }

   }

   public final zzalk zzkW() {
      this.zzkD();
      return this.zzaeh;
   }
}
