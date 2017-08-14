package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsResult;

final class zzakx implements OnCancelListener {
   // $FF: synthetic field
   private JsResult zzacH;

   zzakx(JsResult var1) {
      this.zzacH = var1;
      super();
   }

   public final void onCancel(DialogInterface var1) {
      this.zzacH.cancel();
   }
}
