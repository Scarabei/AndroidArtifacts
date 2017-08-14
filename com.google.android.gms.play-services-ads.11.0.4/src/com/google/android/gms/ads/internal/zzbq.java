package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzeu;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zzbq extends AsyncTask {
   // $FF: synthetic field
   private zzbm zzvf;

   private zzbq(zzbm var1) {
      this.zzvf = var1;
      super();
   }

   private final String zza(Void... var1) {
      try {
         zzbm var10000 = this.zzvf;
         Future var10001 = zzbm.zze(this.zzvf);
         zzme var3 = zzmo.zzFX;
         zzbm.zza(var10000, (zzeu)var10001.get(((Long)zzbs.zzbL().zzd(var3)).longValue(), TimeUnit.MILLISECONDS));
      } catch (ExecutionException | InterruptedException var4) {
         zzafr.zzc("Failed to load ad data", var4);
      } catch (TimeoutException var5) {
         zzafr.zzaT("Timed out waiting for ad data");
      }

      return this.zzvf.zzbp();
   }

   // $FF: synthetic method
   protected final void onPostExecute(Object var1) {
      String var3 = (String)var1;
      if (zzbm.zzf(this.zzvf) != null && var3 != null) {
         zzbm.zzf(this.zzvf).loadUrl(var3);
      }

   }

   // $FF: synthetic method
   protected final Object doInBackground(Object[] var1) {
      return this.zza((Void[])var1);
   }

   // $FF: synthetic method
   zzbq(zzbm var1, zzbn var2) {
      this(var1);
   }
}
