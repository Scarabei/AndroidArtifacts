package com.google.android.gms.internal;

import android.os.Looper;

final class zzann implements Runnable {
   // $FF: synthetic field
   private zzanm zzaha;

   zzann(zzanm var1) {
      this.zzaha = var1;
      super();
   }

   public final void run() {
      if (Looper.myLooper() == Looper.getMainLooper()) {
         zzanm.zza(this.zzaha).zzkt().zzf(this);
      } else {
         boolean var1 = this.zzaha.zzbo();
         zzanm.zza(this.zzaha, 0L);
         if (var1) {
            this.zzaha.run();
         }

      }
   }
}
