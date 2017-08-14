package com.google.android.gms.internal;

import android.os.Bundle;

final class zzcws implements Runnable {
   private boolean zzbJq;
   // $FF: synthetic field
   private String val$name;
   // $FF: synthetic field
   private Bundle zzbJr;
   // $FF: synthetic field
   private String zzbJs;
   // $FF: synthetic field
   private long zzbJt;
   // $FF: synthetic field
   private String zzbth;
   // $FF: synthetic field
   private zzcwr zzbJv;

   zzcws(zzcwr var1, String var2, Bundle var3, String var4, long var5, String var7) {
      this.zzbJv = var1;
      this.val$name = var2;
      this.zzbJr = var3;
      this.zzbJs = var4;
      this.zzbJt = var5;
      this.zzbth = var7;
      super();
      this.zzbJq = false;
   }

   public final void run() {
      if (zzcwn.zza(this.zzbJv.zzbJp) == 3) {
         zzcwn.zzb(this.zzbJv.zzbJp).zza(this.val$name, this.zzbJr, this.zzbJs, this.zzbJt, false);
      } else if (zzcwn.zza(this.zzbJv.zzbJp) != 1 && zzcwn.zza(this.zzbJv.zzbJp) != 2) {
         if (zzcwn.zza(this.zzbJv.zzbJp) == 4) {
            zzcvk.v(String.format("Container failed to load: skipping event listener by ignoring the event: name = %s, origin = %s, params = %s.", this.val$name, this.zzbth, this.zzbJr));
         } else {
            int var1 = zzcwn.zza(this.zzbJv.zzbJp);
            zzcup.zzd((new StringBuilder(28)).append("Unexpected state:").append(var1).toString(), zzcwn.zzd(this.zzbJv.zzbJp));
         }
      } else if (!this.zzbJq) {
         zzcvk.v(String.format("Container not loaded yet: deferring event listener by enqueuing the event: name = %s, origin = %s, params = %s.", this.val$name, this.zzbth, this.zzbJr));
         this.zzbJq = true;
         zzcwn.zze(this.zzbJv.zzbJp).add(this);
      } else {
         zzcvk.zzaT("Invalid state - not expecting to see a deferred event during container loading.");
      }
   }
}
