package com.google.android.gms.ads.internal.js;

import android.content.Context;
import android.support.annotation.Keep;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzajg;
import com.google.android.gms.internal.zzajm;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class JavascriptEngineFactory {
   public final zzajm zza(Context var1, zzaje var2, String var3, zzcu var4, com.google.android.gms.ads.internal.zzv var5) {
      JavascriptEngineFactory.JSEngineSettableFuture var6 = new JavascriptEngineFactory.JSEngineSettableFuture((zzc)null);
      zzagz.zzZr.post(new zzc(this, var1, var2, var4, var5, var6, var3));
      return var6;
   }

   static class JSEngineSettableFuture extends zzajg {
      @Keep
      zza mEngineReference;

      private JSEngineSettableFuture() {
      }

      // $FF: synthetic method
      JSEngineSettableFuture(zzc var1) {
         this();
      }
   }
}
