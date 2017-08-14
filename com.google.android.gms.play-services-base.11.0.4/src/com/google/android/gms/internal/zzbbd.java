package com.google.android.gms.internal;

import android.app.Dialog;

final class zzbbd extends zzbdl {
   // $FF: synthetic field
   private Dialog zzaBT;
   // $FF: synthetic field
   private zzbbc zzaBU;

   zzbbd(zzbbc var1, Dialog var2) {
      this.zzaBU = var1;
      this.zzaBT = var2;
      super();
   }

   public final void zzpA() {
      this.zzaBU.zzaBS.zzpx();
      if (this.zzaBT.isShowing()) {
         this.zzaBT.dismiss();
      }

   }
}
