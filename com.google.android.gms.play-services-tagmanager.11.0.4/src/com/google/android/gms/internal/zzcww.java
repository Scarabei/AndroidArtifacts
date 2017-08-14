package com.google.android.gms.internal;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

final class zzcww implements ComponentCallbacks2 {
   // $FF: synthetic field
   final zzcwn zzbJp;

   zzcww(zzcwn var1) {
      this.zzbJp = var1;
   }

   public final void onTrimMemory(int var1) {
      if (var1 == 20) {
         zzcwn.zzf(this.zzbJp).execute(new zzcwx(this));
      }

   }

   public final void onConfigurationChanged(Configuration var1) {
   }

   public final void onLowMemory() {
   }
}
