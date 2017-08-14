package com.google.android.gms.internal;

final class zzq implements Runnable {
   // $FF: synthetic field
   private String zzO;
   // $FF: synthetic field
   private long zzP;
   // $FF: synthetic field
   private zzp zzQ;

   zzq(zzp var1, String var2, long var3) {
      this.zzQ = var1;
      this.zzO = var2;
      this.zzP = var3;
      super();
   }

   public final void run() {
      zzp.zzb(this.zzQ).zza(this.zzO, this.zzP);
      zzp.zzb(this.zzQ).zzc(this.toString());
   }
}
