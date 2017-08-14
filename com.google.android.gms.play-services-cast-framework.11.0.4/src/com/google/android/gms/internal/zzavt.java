package com.google.android.gms.internal;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.TracksChooserDialogFragment;

final class zzavt implements OnClickListener {
   // $FF: synthetic field
   private zzavs zzavq;

   zzavt(zzavs var1) {
      this.zzavq = var1;
      super();
   }

   public final void onClick(View var1) {
      Activity var2;
      if ((var2 = (Activity)zzavs.zza(this.zzavq).get()) != null) {
         RemoteMediaClient var3;
         if ((var3 = zzavs.zzb(this.zzavq)) != null && var3.hasMediaSession()) {
            if (var2 instanceof FragmentActivity) {
               FragmentActivity var4;
               FragmentTransaction var5 = (var4 = (FragmentActivity)var2).getSupportFragmentManager().beginTransaction();
               Fragment var6;
               if ((var6 = var4.getSupportFragmentManager().findFragmentByTag("TRACKS_CHOOSER_DIALOG_TAG")) != null) {
                  var5.remove(var6);
               }

               var5.addToBackStack((String)null);
               MediaInfo var7 = var3.getMediaInfo();
               long[] var8 = var3.getMediaStatus().getActiveTrackIds();
               TracksChooserDialogFragment var9;
               if ((var9 = TracksChooserDialogFragment.newInstance(var7, var8)) != null) {
                  var9.show(var5, "TRACKS_CHOOSER_DIALOG_TAG");
               }
            }

         }
      }
   }
}
