package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

final class zzaky implements OnClickListener {
   // $FF: synthetic field
   private JsResult zzacH;

   zzaky(JsResult var1) {
      this.zzacH = var1;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      this.zzacH.cancel();
   }
}
