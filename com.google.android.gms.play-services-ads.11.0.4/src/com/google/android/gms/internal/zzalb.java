package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsPromptResult;

final class zzalb implements OnClickListener {
   // $FF: synthetic field
   private JsPromptResult zzacI;

   zzalb(JsPromptResult var1) {
      this.zzacI = var1;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      this.zzacI.cancel();
   }
}
