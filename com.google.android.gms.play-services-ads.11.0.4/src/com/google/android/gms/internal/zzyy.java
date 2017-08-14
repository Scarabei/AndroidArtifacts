package com.google.android.gms.internal;

import java.lang.ref.WeakReference;
import org.json.JSONObject;

final class zzyy implements Runnable {
   // $FF: synthetic field
   final JSONObject zzRZ;
   // $FF: synthetic field
   final zzajg zzSa;
   // $FF: synthetic field
   final zzyx zzSb;

   zzyy(zzyx var1, JSONObject var2, zzajg var3) {
      this.zzSb = var1;
      this.zzRZ = var2;
      this.zzSa = var3;
   }

   public final void run() {
      try {
         zzaka var1 = this.zzSb.zzgz();
         zzyx.zza(this.zzSb).zzd(var1);
         WeakReference var2 = new WeakReference(var1);
         var1.zziw().zza(zzyx.zza(this.zzSb, var2), zzyx.zzb(this.zzSb, var2));
         zzyx.zza(this.zzSb, var1);
         var1.zziw().zza((zzakg)(new zzyz(this, var1)));
         var1.zziw().zza((zzakf)(new zzza(this)));
         zzme var3 = zzmo.zzFs;
         var1.loadUrl((String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3));
      } catch (Exception var4) {
         zzafr.zzc("Exception occurred while getting video view", var4);
         this.zzSa.zzg((Object)null);
      }
   }
}
