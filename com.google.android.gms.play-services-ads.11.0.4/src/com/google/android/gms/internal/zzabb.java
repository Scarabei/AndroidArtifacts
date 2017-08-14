package com.google.android.gms.internal;

import org.json.JSONObject;

final class zzabb implements Runnable {
   // $FF: synthetic field
   final JSONObject zzUd;
   // $FF: synthetic field
   final String zzUe;
   // $FF: synthetic field
   private zzaaz zzUc;

   zzabb(zzaaz var1, JSONObject var2, String var3) {
      this.zzUc = var1;
      this.zzUd = var2;
      this.zzUe = var3;
      super();
   }

   public final void run() {
      zzaaz.zza(this.zzUc, zzaaz.zzgE().zzb((zzcu)null));
      zzaaz.zzb(this.zzUc).zza(new zzabc(this), new zzabd(this));
   }
}
