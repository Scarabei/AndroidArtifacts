package com.google.android.gms.cast.framework.media.uicontroller;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.google.android.gms.internal.zzawv;
import java.util.Iterator;

final class zza implements OnSeekBarChangeListener {
   // $FF: synthetic field
   private UIMediaController zzavl;

   zza(UIMediaController var1) {
      this.zzavl = var1;
      super();
   }

   public final void onStopTrackingTouch(SeekBar var1) {
      Iterator var2 = UIMediaController.zza(this.zzavl).iterator();

      while(var2.hasNext()) {
         ((zzawv)var2.next()).zzY(true);
      }

   }

   public final void onStartTrackingTouch(SeekBar var1) {
      Iterator var2 = UIMediaController.zza(this.zzavl).iterator();

      while(var2.hasNext()) {
         ((zzawv)var2.next()).zzY(false);
      }

   }

   public final void onProgressChanged(SeekBar var1, int var2, boolean var3) {
      if (var3) {
         Iterator var4 = UIMediaController.zza(this.zzavl).iterator();

         while(var4.hasNext()) {
            ((zzawv)var4.next()).zzy((long)var2);
         }
      }

   }
}
