package com.google.android.gms.internal;

import android.graphics.Bitmap;

final class zzavx implements zzavd {
   // $FF: synthetic field
   private zzavw zzavx;

   zzavx(zzavw var1) {
      this.zzavx = var1;
      super();
   }

   public final void zzc(Bitmap var1) {
      if (var1 != null) {
         if (zzavw.zza(this.zzavx) != null) {
            zzavw.zza(this.zzavx).setVisibility(4);
         }

         zzavw.zzb(this.zzavx).setVisibility(0);
         zzavw.zzb(this.zzavx).setImageBitmap(var1);
      }

   }
}
