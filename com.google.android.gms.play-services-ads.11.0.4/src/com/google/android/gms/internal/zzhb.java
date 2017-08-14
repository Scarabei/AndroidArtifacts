package com.google.android.gms.internal;

import android.webkit.ValueCallback;
import android.webkit.WebView;

final class zzhb implements Runnable {
   private ValueCallback zzyK;
   // $FF: synthetic field
   final zzgt zzyL;
   // $FF: synthetic field
   final WebView zzyM;
   // $FF: synthetic field
   final boolean zzyN;
   // $FF: synthetic field
   final zzgz zzyJ;

   zzhb(zzgz var1, zzgt var2, WebView var3, boolean var4) {
      this.zzyJ = var1;
      this.zzyL = var2;
      this.zzyM = var3;
      this.zzyN = var4;
      this.zzyK = new zzhc(this);
   }

   public final void run() {
      if (this.zzyM.getSettings().getJavaScriptEnabled()) {
         try {
            this.zzyM.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzyK);
            return;
         } catch (Throwable var1) {
            this.zzyK.onReceiveValue("");
         }
      }

   }
}
