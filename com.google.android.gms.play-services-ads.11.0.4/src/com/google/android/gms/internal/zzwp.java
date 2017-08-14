package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class zzwp implements OnClickListener {
   // $FF: synthetic field
   private zzwn zzNQ;

   zzwp(zzwn var1) {
      this.zzNQ = var1;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      this.zzNQ.zzan("User canceled the download.");
   }
}
