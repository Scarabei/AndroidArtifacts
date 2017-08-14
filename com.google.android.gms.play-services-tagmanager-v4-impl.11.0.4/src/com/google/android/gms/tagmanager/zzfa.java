package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ee;

final class zzfa implements Runnable {
   // $FF: synthetic field
   private ee zzbGd;
   // $FF: synthetic field
   private zzey zzbGc;

   zzfa(zzey var1, ee var2) {
      this.zzbGc = var1;
      this.zzbGd = var2;
      super();
   }

   public final void run() {
      this.zzbGc.zzb(this.zzbGd);
   }
}
