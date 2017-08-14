package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzir;
import java.lang.ref.WeakReference;

final class zzbj implements Runnable {
   // $FF: synthetic field
   private WeakReference zzuX;
   // $FF: synthetic field
   private zzbi zzuY;

   zzbj(zzbi var1, WeakReference var2) {
      this.zzuY = var1;
      this.zzuX = var2;
      super();
   }

   public final void run() {
      zzbi.zza(this.zzuY, false);
      zza var1;
      if ((var1 = (zza)this.zzuX.get()) != null) {
         zzir var3 = zzbi.zza(this.zzuY);
         if (var1.zzb(var3)) {
            var1.zza(var3);
            return;
         }

         zzafr.zzaS("Ad is not visible. Not refreshing ad.");
         var1.zzsO.zzg(var3);
      }

   }
}
