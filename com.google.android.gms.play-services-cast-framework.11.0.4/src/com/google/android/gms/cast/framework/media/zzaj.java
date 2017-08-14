package com.google.android.gms.cast.framework.media;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class zzaj implements OnClickListener {
   // $FF: synthetic field
   private TracksChooserDialogFragment zzauJ;

   zzaj(TracksChooserDialogFragment var1) {
      this.zzauJ = var1;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      if (TracksChooserDialogFragment.zza(this.zzauJ) != null) {
         TracksChooserDialogFragment.zza(this.zzauJ).cancel();
         TracksChooserDialogFragment.zza((TracksChooserDialogFragment)this.zzauJ, (Dialog)null);
      }

   }
}
