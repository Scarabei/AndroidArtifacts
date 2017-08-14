package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;

final class zzcwq implements Runnable {
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
   private zzcwp zzbJu;

   zzcwq(zzcwp var1, String var2, Bundle var3, String var4, long var5, String var7) {
      this.zzbJu = var1;
      this.val$name = var2;
      this.zzbJr = var3;
      this.zzbJs = var4;
      this.zzbJt = var5;
      this.zzbth = var7;
      super();
      this.zzbJq = false;
   }

   public final void run() {
      if (zzcwn.zza(this.zzbJu.zzbJp) == 3) {
         zzcwn.zzb(this.zzbJu.zzbJp).zza(this.val$name, this.zzbJr, this.zzbJs, this.zzbJt, true);
      } else if (zzcwn.zza(this.zzbJu.zzbJp) == 4) {
         zzcvk.v(String.format("Container failed to load: skipping  event interceptor by logging event back to Firebase directly: name = %s, origin = %s, params = %s.", this.val$name, this.zzbJs, this.zzbJr));

         try {
            zzcwn.zzc(this.zzbJu.zzbJp).logEventInternalNoInterceptor(this.zzbJs, this.val$name, this.zzbJr, this.zzbJt);
         } catch (RemoteException var2) {
            zzcup.zza("Error logging event on measurement proxy: ", var2, zzcwn.zzd(this.zzbJu.zzbJp));
         }
      } else if (zzcwn.zza(this.zzbJu.zzbJp) != 1 && zzcwn.zza(this.zzbJu.zzbJp) != 2) {
         int var1 = zzcwn.zza(this.zzbJu.zzbJp);
         zzcup.zzd((new StringBuilder(28)).append("Unexpected state:").append(var1).toString(), zzcwn.zzd(this.zzbJu.zzbJp));
      } else if (!this.zzbJq) {
         zzcvk.v(String.format("Container not loaded yet: deferring event interceptor by enqueuing the event: name = %s, origin = %s, params = %s.", this.val$name, this.zzbth, this.zzbJr));
         this.zzbJq = true;
         zzcwn.zze(this.zzbJu.zzbJp).add(this);
      } else {
         zzcup.zzd("Invalid state - not expecting to see a deferredevent during container loading.", zzcwn.zzd(this.zzbJu.zzbJp));
      }
   }
}
