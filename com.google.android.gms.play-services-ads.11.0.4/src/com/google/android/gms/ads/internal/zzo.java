package com.google.android.gms.ads.internal;

import android.view.View;
import android.view.View.OnClickListener;

final class zzo implements OnClickListener {
   // $FF: synthetic field
   private zzw zztf;
   // $FF: synthetic field
   private zzl zzte;

   zzo(zzl var1, zzw var2) {
      this.zzte = var1;
      this.zztf = var2;
      super();
   }

   public final void onClick(View var1) {
      this.zztf.recordClick();
      if (this.zzte.zztc != null) {
         this.zzte.zztc.zzha();
      }

   }
}
