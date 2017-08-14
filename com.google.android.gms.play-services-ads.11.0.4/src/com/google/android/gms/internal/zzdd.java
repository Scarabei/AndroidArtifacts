package com.google.android.gms.internal;

final class zzdd implements Runnable {
   // $FF: synthetic field
   private int zzqX;
   // $FF: synthetic field
   private boolean zzqY;
   // $FF: synthetic field
   private zzdb zzqW;

   zzdd(zzdb var1, int var2, boolean var3) {
      this.zzqW = var1;
      this.zzqX = var2;
      this.zzqY = var3;
      super();
   }

   public final void run() {
      zzax var1 = this.zzqW.zzb(this.zzqX, this.zzqY);
      zzdb.zza(this.zzqW, var1);
      if (zzdb.zzb(this.zzqX, var1)) {
         this.zzqW.zza(this.zzqX + 1, this.zzqY);
      }

   }
}
