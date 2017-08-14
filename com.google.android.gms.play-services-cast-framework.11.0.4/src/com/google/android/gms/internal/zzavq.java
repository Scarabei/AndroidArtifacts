package com.google.android.gms.internal;

import android.content.Intent;
import android.support.v4.media.session.MediaSessionCompat.Callback;
import android.view.KeyEvent;

final class zzavq extends Callback {
   // $FF: synthetic field
   private zzavn zzave;

   zzavq(zzavn var1) {
      this.zzave = var1;
      super();
   }

   public final boolean onMediaButtonEvent(Intent var1) {
      KeyEvent var2;
      if ((var2 = (KeyEvent)var1.getParcelableExtra("android.intent.extra.KEY_EVENT")) != null && (var2.getKeyCode() == 127 || var2.getKeyCode() == 126)) {
         zzavn.zza(this.zzave).togglePlayback();
      }

      return true;
   }

   public final void onPlay() {
      zzavn.zza(this.zzave).togglePlayback();
   }

   public final void onPause() {
      zzavn.zza(this.zzave).togglePlayback();
   }
}
