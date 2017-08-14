package com.google.android.gms.internal;

import android.net.Uri;

final class zzcwy implements Runnable {
   // $FF: synthetic field
   private Uri zzbJB;
   // $FF: synthetic field
   private zzcwn zzbJp;

   zzcwy(zzcwn var1, Uri var2) {
      this.zzbJp = var1;
      this.zzbJB = var2;
      super();
   }

   public final void run() {
      String var1 = String.valueOf(this.zzbJB);
      zzcvk.v((new StringBuilder(25 + String.valueOf(var1).length())).append("Preview requested to uri ").append(var1).toString());
      synchronized(zzcwn.zzg(this.zzbJp)) {
         if (zzcwn.zza(this.zzbJp) == 2) {
            zzcvk.v("Still initializing. Defer preview container loading.");
            zzcwn.zze(this.zzbJp).add(this);
            return;
         }

         String var2;
         if ((var2 = (String)zzcwn.zza((zzcwn)this.zzbJp, (String[])null).first) == null) {
            zzcvk.zzaT("Preview failed (no container found)");
            return;
         }

         String var3;
         if (!zzcwn.zzh(this.zzbJp).zzc(var2, this.zzbJB)) {
            var3 = String.valueOf(this.zzbJB);
            zzcvk.zzaT((new StringBuilder(73 + String.valueOf(var3).length())).append("Cannot preview the app with the uri: ").append(var3).append(". Launching current version instead.").toString());
            return;
         }

         if (!zzcwn.zzi(this.zzbJp)) {
            var3 = String.valueOf(this.zzbJB);
            zzcvk.v((new StringBuilder(84 + String.valueOf(var3).length())).append("Deferring container loading for preview uri: ").append(var3).append("(Tag Manager has not been initialized).").toString());
            return;
         }

         var3 = String.valueOf(this.zzbJB);
         zzcvk.zzaS((new StringBuilder(36 + String.valueOf(var3).length())).append("Starting to load preview container: ").append(var3).toString());
         if (!zzcwn.zzb(this.zzbJp).zzCF()) {
            zzcvk.zzaT("Failed to reset TagManager service for preview");
            return;
         }

         zzcwn.zza(this.zzbJp, false);
         zzcwn.zza(this.zzbJp, 1);
      }

      this.zzbJp.initialize();
   }
}
