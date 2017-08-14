package com.google.android.gms.internal;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawl extends UIController implements RemoteMediaClient.ProgressListener {
   private final SeekBar zzavB;
   private final long zzavQ;
   private final OnSeekBarChangeListener zzavR;
   private boolean mIsAttached = true;

   public zzawl(SeekBar var1, long var2, OnSeekBarChangeListener var4) {
      this.zzavB = var1;
      this.zzavQ = var2;
      this.zzavR = new zzawm(this, var4);
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.zzavB.setOnSeekBarChangeListener(this.zzavR);
      RemoteMediaClient var2;
      if ((var2 = this.getRemoteMediaClient()) != null) {
         var2.addProgressListener(this, this.zzavQ);
         if (var2.hasMediaSession()) {
            this.zzavB.setMax((int)var2.getStreamDuration());
            this.zzavB.setProgress((int)var2.getApproximateStreamPosition());
            return;
         }

         this.zzavB.setMax(1);
         this.zzavB.setProgress(0);
      }

   }

   public final void onSessionEnded() {
      if (this.getRemoteMediaClient() != null) {
         this.getRemoteMediaClient().removeProgressListener(this);
      }

      this.zzavB.setOnSeekBarChangeListener((OnSeekBarChangeListener)null);
      this.zzavB.setMax(1);
      this.zzavB.setProgress(0);
      super.onSessionEnded();
   }

   public final void onSendingRemoteMediaRequest() {
   }

   public final void onProgressUpdated(long var1, long var3) {
      if (this.mIsAttached) {
         this.zzavB.setMax((int)var3);
         this.zzavB.setProgress((int)var1);
      }

   }

   public final void zzY(boolean var1) {
      this.mIsAttached = var1;
   }

   // $FF: synthetic method
   static RemoteMediaClient zza(zzawl var0) {
      return var0.getRemoteMediaClient();
   }
}
