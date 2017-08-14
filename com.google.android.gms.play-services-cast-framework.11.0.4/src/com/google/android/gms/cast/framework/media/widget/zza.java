package com.google.android.gms.cast.framework.media.widget;

import android.graphics.Bitmap;
import com.google.android.gms.internal.zzavd;

final class zza implements zzavd {
   // $FF: synthetic field
   private ExpandedControllerActivity zzawF;

   zza(ExpandedControllerActivity var1) {
      this.zzawF = var1;
      super();
   }

   public final void zzc(Bitmap var1) {
      if (var1 != null) {
         if (ExpandedControllerActivity.zza(this.zzawF) != null) {
            ExpandedControllerActivity.zza(this.zzawF).setVisibility(8);
         }

         if (ExpandedControllerActivity.zzb(this.zzawF) != null) {
            ExpandedControllerActivity.zzb(this.zzawF).setVisibility(0);
            ExpandedControllerActivity.zzb(this.zzawF).setImageBitmap(var1);
         }
      }

   }
}
