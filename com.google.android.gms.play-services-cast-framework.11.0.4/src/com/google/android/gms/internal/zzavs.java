package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.R.string;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

public final class zzavs extends UIController {
   private final View mView;
   private final WeakReference zzavm;
   private final String zzavn;
   private final String zzavo;
   private final OnClickListener zzavp;

   public zzavs(View var1, Activity var2) {
      this.mView = var1;
      this.zzavn = var2.getString(string.cast_closed_captions);
      this.zzavo = var2.getString(string.cast_closed_captions_unavailable);
      this.zzavm = new WeakReference(var2);
      this.zzavp = new zzavt(this);
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.mView.setOnClickListener(this.zzavp);
      this.zzok();
   }

   public final void onSessionEnded() {
      this.mView.setOnClickListener((OnClickListener)null);
      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zzok();
   }

   public final void onSendingRemoteMediaRequest() {
      this.mView.setEnabled(false);
   }

   private final void zzok() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
         boolean var10000;
         label40: {
            MediaInfo var2;
            if ((var2 = var1.getMediaInfo()) != null) {
               List var3;
               if ((var3 = var2.getMediaTracks()) == null || var3.isEmpty()) {
                  var10000 = false;
                  break label40;
               }

               int var4 = 0;
               Iterator var5 = var3.iterator();

               while(var5.hasNext()) {
                  MediaTrack var6;
                  if ((var6 = (MediaTrack)var5.next()).getType() == 2) {
                     ++var4;
                     if (var4 > 1) {
                        var10000 = true;
                        break label40;
                     }
                  } else if (var6.getType() == 1) {
                     var10000 = true;
                     break label40;
                  }
               }
            }

            var10000 = false;
         }

         if (var10000 && !var1.isPlayingAd()) {
            this.mView.setEnabled(true);
            this.mView.setContentDescription(this.zzavn);
            return;
         }
      }

      this.mView.setEnabled(false);
      this.mView.setContentDescription(this.zzavo);
   }

   // $FF: synthetic method
   static WeakReference zza(zzavs var0) {
      return var0.zzavm;
   }

   // $FF: synthetic method
   static RemoteMediaClient zzb(zzavs var0) {
      return var0.getRemoteMediaClient();
   }
}
