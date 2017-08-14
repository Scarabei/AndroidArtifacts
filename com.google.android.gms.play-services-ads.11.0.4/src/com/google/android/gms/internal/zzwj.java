package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class zzwj implements OnClickListener {
   // $FF: synthetic field
   private zzwh zzNx;

   zzwj(zzwh var1) {
      this.zzNx = var1;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      this.zzNx.zzan("Operation denied by user.");
   }
}
