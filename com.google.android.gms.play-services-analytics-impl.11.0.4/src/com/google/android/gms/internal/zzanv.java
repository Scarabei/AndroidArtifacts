package com.google.android.gms.internal;

final class zzanv implements zzanj {
   private final zzamj zzafJ;
   private final zzanw zzahX;

   public zzanv(zzamj var1) {
      this.zzafJ = var1;
      this.zzahX = new zzanw();
   }

   public final void zzl(String var1, String var2) {
   }

   public final void zzm(String var1, String var2) {
      if ("ga_appName".equals(var1)) {
         this.zzahX.zzaeH = var2;
      } else if ("ga_appVersion".equals(var1)) {
         this.zzahX.zzaeI = var2;
      } else if ("ga_logLevel".equals(var1)) {
         this.zzahX.zzahY = var2;
      } else {
         this.zzafJ.zzkr().zzd("String xml configuration name not recognized", var1);
      }
   }

   public final void zze(String var1, boolean var2) {
      if ("ga_dryRun".equals(var1)) {
         this.zzahX.zzaia = var2 ? 1 : 0;
      } else {
         this.zzafJ.zzkr().zzd("Bool xml configuration name not recognized", var1);
      }
   }

   public final void zzd(String var1, int var2) {
      if ("ga_dispatchPeriod".equals(var1)) {
         this.zzahX.zzahZ = var2;
      } else {
         this.zzafJ.zzkr().zzd("Int xml configuration name not recognized", var1);
      }
   }

   // $FF: synthetic method
   public final zzanh zzlm() {
      return this.zzahX;
   }
}
