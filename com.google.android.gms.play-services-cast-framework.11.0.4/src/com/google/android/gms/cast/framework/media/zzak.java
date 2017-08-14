package com.google.android.gms.cast.framework.media;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class zzak implements OnClickListener {
   // $FF: synthetic field
   private zzal zzauK;
   // $FF: synthetic field
   private zzal zzauL;
   // $FF: synthetic field
   private TracksChooserDialogFragment zzauJ;

   zzak(TracksChooserDialogFragment var1, zzal var2, zzal var3) {
      this.zzauJ = var1;
      this.zzauK = var2;
      this.zzauL = var3;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      TracksChooserDialogFragment.zza(this.zzauJ, this.zzauK, this.zzauL);
   }
}
