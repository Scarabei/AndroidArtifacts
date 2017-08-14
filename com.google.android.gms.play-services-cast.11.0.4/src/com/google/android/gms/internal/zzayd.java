package com.google.android.gms.internal;

import com.google.android.gms.cast.Cast;

final class zzayd implements Runnable {
   // $FF: synthetic field
   private zzaxx zzayi;
   // $FF: synthetic field
   private String zzaoQ;
   // $FF: synthetic field
   private String zzaym;

   zzayd(zzaxz var1, zzaxx var2, String var3, String var4) {
      this.zzayi = var2;
      this.zzaoQ = var3;
      this.zzaym = var4;
      super();
   }

   public final void run() {
      Cast.MessageReceivedCallback var1;
      synchronized(zzaxx.zze(this.zzayi)) {
         var1 = (Cast.MessageReceivedCallback)zzaxx.zze(this.zzayi).get(this.zzaoQ);
      }

      if (var1 != null) {
         var1.onMessageReceived(zzaxx.zzf(this.zzayi), this.zzaoQ, this.zzaym);
      } else {
         zzaxx.zzoF().zzb("Discarded message for unknown namespace '%s'", this.zzaoQ);
      }
   }
}
