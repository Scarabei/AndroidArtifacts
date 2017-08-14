package com.google.android.gms.internal;

import java.util.Iterator;

final class zzcxc implements Runnable {
   // $FF: synthetic field
   private zzcut zzbJI;
   // $FF: synthetic field
   private zzcxa zzbJH;

   zzcxc(zzcxa var1, zzcut var2) {
      this.zzbJH = var1;
      this.zzbJI = var2;
      super();
   }

   public final void run() {
      if (zzcxa.zza(this.zzbJH).isEmpty()) {
         zzcvk.e("TagManagerBackend emit called without loaded container.");
      } else {
         Iterator var1 = zzcxa.zza(this.zzbJH).values().iterator();

         while(var1.hasNext()) {
            ((zzcuf)var1.next()).zza(this.zzbJI);
         }

      }
   }
}
