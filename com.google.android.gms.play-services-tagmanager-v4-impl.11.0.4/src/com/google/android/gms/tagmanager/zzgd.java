package com.google.android.gms.tagmanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

final class zzgd implements ComponentCallbacks2 {
   // $FF: synthetic field
   private TagManager zzbGX;

   zzgd(TagManager var1) {
      this.zzbGX = var1;
      super();
   }

   public final void onTrimMemory(int var1) {
      if (var1 == 20) {
         this.zzbGX.dispatch();
      }

   }

   public final void onConfigurationChanged(Configuration var1) {
   }

   public final void onLowMemory() {
   }
}
