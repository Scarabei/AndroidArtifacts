package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsPromptResult;
import android.widget.EditText;

final class zzalc implements OnClickListener {
   // $FF: synthetic field
   private JsPromptResult zzacI;
   // $FF: synthetic field
   private EditText zzacJ;

   zzalc(JsPromptResult var1, EditText var2) {
      this.zzacI = var1;
      this.zzacJ = var2;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      this.zzacI.confirm(this.zzacJ.getText().toString());
   }
}
