package com.google.android.gms.internal;

import android.database.ContentObserver;
import android.os.Handler;

final class zzfl extends ContentObserver {
   // $FF: synthetic field
   private zzfi zzxh;

   public zzfl(zzfi var1, Handler var2) {
      this.zzxh = var1;
      super(var2);
   }

   public final void onChange(boolean var1) {
      super.onChange(var1);
      this.zzxh.zzcp();
   }
}
