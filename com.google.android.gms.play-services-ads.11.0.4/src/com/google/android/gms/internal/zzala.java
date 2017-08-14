package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsPromptResult;

final class zzala implements OnCancelListener {
   // $FF: synthetic field
   private JsPromptResult zzacI;

   zzala(JsPromptResult var1) {
      this.zzacI = var1;
      super();
   }

   public final void onCancel(DialogInterface var1) {
      this.zzacI.cancel();
   }
}
