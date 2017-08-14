package com.google.android.gms.analytics;

import java.util.Iterator;

final class zzm implements Runnable {
   // $FF: synthetic field
   private zzi zzaej;
   // $FF: synthetic field
   private zzl zzaek;

   zzm(zzl var1, zzi var2) {
      this.zzaek = var1;
      this.zzaej = var2;
      super();
   }

   public final void run() {
      this.zzaej.zzjw().zza(this.zzaej);
      Iterator var1 = zzl.zza(this.zzaek).iterator();

      while(var1.hasNext()) {
         var1.next();
      }

      zzl.zza(this.zzaek, this.zzaej);
   }
}
