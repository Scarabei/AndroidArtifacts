package com.google.android.gms.internal;

final class zzamq implements Runnable {
   // $FF: synthetic field
   private zzany zzagn;
   // $FF: synthetic field
   private zzamp zzago;

   zzamq(zzamp var1, zzany var2) {
      this.zzago = var1;
      this.zzagn = var2;
      super();
   }

   public final void run() {
      if (!this.zzago.zzagk.isConnected()) {
         this.zzago.zzagk.zzbp("Connected to service after a timeout");
         zzamn.zza(this.zzago.zzagk, this.zzagn);
      }

   }
}
