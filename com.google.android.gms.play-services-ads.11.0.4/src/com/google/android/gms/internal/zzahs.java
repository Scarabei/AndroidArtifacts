package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;

final class zzahs implements OnClickListener {
   // $FF: synthetic field
   private String zzZJ;
   // $FF: synthetic field
   private zzahq zzZI;

   zzahs(zzahq var1, String var2) {
      this.zzZI = var1;
      this.zzZJ = var2;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      com.google.android.gms.ads.internal.zzbs.zzbz();
      zzagz.zzb(zzahq.zzd(this.zzZI), Intent.createChooser((new Intent("android.intent.action.SEND")).setType("text/plain").putExtra("android.intent.extra.TEXT", this.zzZJ), "Share via"));
   }
}
