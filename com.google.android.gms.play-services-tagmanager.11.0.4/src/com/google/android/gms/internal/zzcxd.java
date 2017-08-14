package com.google.android.gms.internal;

import java.util.Iterator;

final class zzcxd implements Runnable {
   // $FF: synthetic field
   private zzcxa zzbJH;

   zzcxd(zzcxa var1) {
      this.zzbJH = var1;
      super();
   }

   public final void run() {
      if (zzcxa.zza(this.zzbJH).isEmpty()) {
         zzcvk.zzaT("TagManagerBackend dispatch called without loaded container.");
      } else {
         Iterator var1 = zzcxa.zza(this.zzbJH).values().iterator();

         while(var1.hasNext()) {
            ((zzcuf)var1.next()).dispatch();
         }

      }
   }
}
