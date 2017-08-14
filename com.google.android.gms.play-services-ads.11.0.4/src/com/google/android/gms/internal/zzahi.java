package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzahi implements Callable {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private WebSettings zzZA;

   zzahi(zzahh var1, Context var2, WebSettings var3) {
      this.zztF = var2;
      this.zzZA = var3;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      if (this.zztF.getCacheDir() != null) {
         this.zzZA.setAppCachePath(this.zztF.getCacheDir().getAbsolutePath());
         this.zzZA.setAppCacheMaxSize(0L);
         this.zzZA.setAppCacheEnabled(true);
      }

      this.zzZA.setDatabasePath(this.zztF.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
      this.zzZA.setDatabaseEnabled(true);
      this.zzZA.setDomStorageEnabled(true);
      this.zzZA.setDisplayZoomControls(false);
      this.zzZA.setBuiltInZoomControls(true);
      this.zzZA.setSupportZoom(true);
      this.zzZA.setAllowContentAccess(false);
      return true;
   }
}
