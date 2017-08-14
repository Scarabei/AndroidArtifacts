package com.google.android.gms.internal;

final class zzcwx implements Runnable {
   // $FF: synthetic field
   private zzcww zzbJA;

   zzcwx(zzcww var1) {
      this.zzbJA = var1;
      super();
   }

   public final void run() {
      zzcvk.v("App's UI deactivated. Dispatching hits.");
      zzcwn.zzb(this.zzbJA.zzbJp).dispatch();
   }
}
