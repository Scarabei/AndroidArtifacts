package com.google.android.gms.internal;

import android.view.View;

final class zzha implements Runnable {
   // $FF: synthetic field
   private View zzyI;
   // $FF: synthetic field
   private zzgz zzyJ;

   zzha(zzgz var1, View var2) {
      this.zzyJ = var1;
      this.zzyI = var2;
      super();
   }

   public final void run() {
      this.zzyJ.zzf(this.zzyI);
   }
}
