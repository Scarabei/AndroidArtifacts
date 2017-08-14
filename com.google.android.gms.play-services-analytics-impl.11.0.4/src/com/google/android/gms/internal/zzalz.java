package com.google.android.gms.internal;

final class zzalz implements Runnable {
   // $FF: synthetic field
   private int zzafC;
   // $FF: synthetic field
   private zzaly zzafD;

   zzalz(zzaly var1, int var2) {
      this.zzafD = var1;
      this.zzafC = var2;
      super();
   }

   public final void run() {
      zzaly.zza(this.zzafD).zzr((long)this.zzafC * 1000L);
   }
}
