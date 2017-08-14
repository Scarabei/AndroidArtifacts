package com.google.android.gms.ads.internal;

import android.webkit.CookieManager;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import java.util.concurrent.Callable;

final class zze implements Callable {
   // $FF: synthetic field
   private zzd zzsZ;

   zze(zzd var1) {
      this.zzsZ = var1;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      String var2 = "";
      zzme var4 = zzmo.zzGf;
      CookieManager var3;
      if (((Boolean)zzbs.zzbL().zzd(var4)).booleanValue() && (var3 = zzbs.zzbB().zzS(this.zzsZ.zzsP.zzqD)) != null) {
         var2 = var3.getCookie("googleads.g.doubleclick.net");
      }

      return var2;
   }
}
