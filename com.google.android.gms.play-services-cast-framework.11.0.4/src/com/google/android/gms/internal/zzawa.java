package com.google.android.gms.internal;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.util.zzq;

public final class zzawa extends UIController {
   private final SeekBar zzavA;
   private final SeekBar zzavB;

   public zzawa(SeekBar var1, SeekBar var2) {
      this.zzavA = var1;
      this.zzavB = var2;
      this.zzavA.setClickable(false);
      if (zzq.zzsc()) {
         this.zzavA.setThumb((Drawable)null);
      } else {
         this.zzavA.setThumb(new ColorDrawable(0));
      }

      this.zzavA.setMax(1);
      this.zzavA.setProgress(1);
      this.zzavA.setOnTouchListener(new zzawb(this));
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.zzom();
   }

   public final void onMediaStatusUpdated() {
      this.zzom();
   }

   private final void zzom() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
         boolean var2 = var1.isLiveStream();
         this.zzavA.setVisibility(var2 ? 0 : 4);
         this.zzavB.setVisibility(var2 ? 4 : 0);
      }

   }
}
