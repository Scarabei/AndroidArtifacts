package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

final class zzakz implements OnClickListener {
   // $FF: synthetic field
   private JsResult zzacH;

   zzakz(JsResult var1) {
      this.zzacH = var1;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      this.zzacH.confirm();
   }
}
