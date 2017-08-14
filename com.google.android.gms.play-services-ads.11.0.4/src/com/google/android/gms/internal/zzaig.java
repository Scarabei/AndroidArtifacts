package com.google.android.gms.internal;

final class zzaig implements zzu {
   // $FF: synthetic field
   private String zzsD;
   // $FF: synthetic field
   private zzain zzaag;

   zzaig(zzaie var1, String var2, zzain var3) {
      this.zzsD = var2;
      this.zzaag = var3;
      super();
   }

   public final void zzd(zzaa var1) {
      String var2 = this.zzsD;
      String var3 = String.valueOf(var1.toString());
      zzafr.zzaT((new StringBuilder(21 + String.valueOf(var2).length() + String.valueOf(var3).length())).append("Failed to load URL: ").append(var2).append("\n").append(var3).toString());
      this.zzaag.zzb((Object)null);
   }
}
