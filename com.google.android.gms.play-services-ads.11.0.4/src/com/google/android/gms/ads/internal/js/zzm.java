package com.google.android.gms.ads.internal.js;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzais;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzcu;

final class zzm implements Runnable {
   // $FF: synthetic field
   final zzcu zzLf;
   // $FF: synthetic field
   final zzac zzLg;
   // $FF: synthetic field
   final zzl zzLh;

   zzm(zzl var1, zzcu var2, zzac var3) {
      this.zzLh = var1;
      this.zzLf = var2;
      this.zzLg = var3;
   }

   public final void run() {
      zze var1;
      try {
         Context var10000 = zzl.zza(this.zzLh);
         zzaje var10001 = zzl.zzb(this.zzLh);
         zzcu var6 = this.zzLf;
         zzaje var5 = var10001;
         Context var4 = var10000;
         var1 = new zze(var4, var5, var6, (com.google.android.gms.ads.internal.zzv)null);
      } catch (Throwable var7) {
         zzafr.zzb("Error creating webview.", var7);
         zzbs.zzbD().zza(var7, "SdkJavascriptFactory.loadJavascriptEngine");
         this.zzLg.reject();
         return;
      }

      var1.zza(new zzn(this, var1));
      var1.zza("/jsLoaded", new zzq(this, var1));
      zzais var2 = new zzais();
      zzr var3 = new zzr(this, var1, var2);
      var2.set(var3);
      var1.zza("/requestReload", var3);
      if (zzl.zzf(this.zzLh).endsWith(".js")) {
         var1.zzac(zzl.zzf(this.zzLh));
      } else if (zzl.zzf(this.zzLh).startsWith("<html>")) {
         var1.zzae(zzl.zzf(this.zzLh));
      } else {
         var1.zzad(zzl.zzf(this.zzLh));
      }

      zzagz.zzZr.postDelayed(new zzs(this, var1), (long)zzw.zzLp);
   }
}
