package com.google.android.gms.internal;

import android.webkit.ValueCallback;

final class zzhc implements ValueCallback {
   // $FF: synthetic field
   private zzhb zzyO;

   zzhc(zzhb var1) {
      this.zzyO = var1;
      super();
   }

   // $FF: synthetic method
   public final void onReceiveValue(Object var1) {
      String var3 = (String)var1;
      this.zzyO.zzyJ.zza(this.zzyO.zzyL, this.zzyO.zzyM, var3, this.zzyO.zzyN);
   }
}
