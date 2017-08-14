package com.google.android.gms.internal;

final class zzbep implements Runnable {
   // $FF: synthetic field
   private zzbds zzaEK;
   // $FF: synthetic field
   private String zzO;
   // $FF: synthetic field
   private zzbeo zzaEZ;

   zzbep(zzbeo var1, zzbds var2, String var3) {
      this.zzaEZ = var1;
      this.zzaEK = var2;
      this.zzO = var3;
      super();
   }

   public final void run() {
      if (zzbeo.zza(this.zzaEZ) > 0) {
         this.zzaEK.onCreate(zzbeo.zzb(this.zzaEZ) != null ? zzbeo.zzb(this.zzaEZ).getBundle(this.zzO) : null);
      }

      if (zzbeo.zza(this.zzaEZ) >= 2) {
         this.zzaEK.onStart();
      }

      if (zzbeo.zza(this.zzaEZ) >= 3) {
         this.zzaEK.onResume();
      }

      if (zzbeo.zza(this.zzaEZ) >= 4) {
         this.zzaEK.onStop();
      }

      if (zzbeo.zza(this.zzaEZ) >= 5) {
         this.zzaEK.onDestroy();
      }

   }
}
