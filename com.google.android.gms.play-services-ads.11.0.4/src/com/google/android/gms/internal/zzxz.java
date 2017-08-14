package com.google.android.gms.internal;

import android.content.Context;

@zzzn
public class zzxz extends zzxr implements zzakf {
   zzxz(Context var1, zzafg var2, zzaka var3, zzxy var4) {
      super(var1, var2, var3, var4);
   }

   protected final void zzgo() {
      if (this.zzQR.errorCode == -2) {
         this.zzJH.zziw().zza((zzakf)this);
         this.zzgq();
         zzafr.zzaC("Loading HTML in WebView.");
         this.zzJH.loadDataWithBaseURL(this.zzQR.zzPi, this.zzQR.body, "text/html", "UTF-8", (String)null);
      }
   }

   protected void zzgq() {
   }
}
