package com.google.android.gms.internal;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

final class zzawm implements OnSeekBarChangeListener {
   // $FF: synthetic field
   private OnSeekBarChangeListener zzavS;
   // $FF: synthetic field
   private zzawl zzavT;

   zzawm(zzawl var1, OnSeekBarChangeListener var2) {
      this.zzavT = var1;
      this.zzavS = var2;
      super();
   }

   public final void onStopTrackingTouch(SeekBar var1) {
      this.zzavT.zzY(true);
      if (this.zzavS != null) {
         this.zzavS.onStopTrackingTouch(var1);
      }

      RemoteMediaClient var2;
      if ((var2 = zzawl.zza(this.zzavT)) != null && var2.hasMediaSession()) {
         var2.seek((long)var1.getProgress());
      }

   }

   public final void onStartTrackingTouch(SeekBar var1) {
      this.zzavT.zzY(false);
      if (this.zzavS != null) {
         this.zzavS.onStartTrackingTouch(var1);
      }

   }

   public final void onProgressChanged(SeekBar var1, int var2, boolean var3) {
      if (this.zzavS != null) {
         this.zzavS.onProgressChanged(var1, var2, var3);
      }

   }
}
