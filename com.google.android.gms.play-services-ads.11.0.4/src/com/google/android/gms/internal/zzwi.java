package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;

final class zzwi implements OnClickListener {
   // $FF: synthetic field
   private zzwh zzNx;

   zzwi(zzwh var1) {
      this.zzNx = var1;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      Intent var3 = this.zzNx.createIntent();
      com.google.android.gms.ads.internal.zzbs.zzbz();
      zzagz.zzb(zzwh.zza(this.zzNx), var3);
   }
}
