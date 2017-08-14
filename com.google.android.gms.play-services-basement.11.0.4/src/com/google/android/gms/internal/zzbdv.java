package com.google.android.gms.internal;

final class zzbdv implements Runnable {
   // $FF: synthetic field
   private zzbds zzaEK;
   // $FF: synthetic field
   private String zzO;
   // $FF: synthetic field
   private zzbdu zzaEL;

   zzbdv(zzbdu var1, zzbds var2, String var3) {
      this.zzaEL = var1;
      this.zzaEK = var2;
      this.zzO = var3;
      super();
   }

   public final void run() {
      if (zzbdu.zza(this.zzaEL) > 0) {
         this.zzaEK.onCreate(zzbdu.zzb(this.zzaEL) != null ? zzbdu.zzb(this.zzaEL).getBundle(this.zzO) : null);
      }

      if (zzbdu.zza(this.zzaEL) >= 2) {
         this.zzaEK.onStart();
      }

      if (zzbdu.zza(this.zzaEL) >= 3) {
         this.zzaEK.onResume();
      }

      if (zzbdu.zza(this.zzaEL) >= 4) {
         this.zzaEK.onStop();
      }

      if (zzbdu.zza(this.zzaEL) >= 5) {
         this.zzaEK.onDestroy();
      }

   }
}
