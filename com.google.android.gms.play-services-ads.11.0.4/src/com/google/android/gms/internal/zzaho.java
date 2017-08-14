package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.CookieManager;

@TargetApi(21)
public final class zzaho extends zzahn {
   public final CookieManager zzS(Context var1) {
      try {
         return CookieManager.getInstance();
      } catch (Exception var3) {
         zzafr.zzb("Failed to obtain CookieManager.", var3);
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var3, (String)"ApiLevelUtil.getCookieManager");
         return null;
      }
   }

   public final zzakb zzb(zzaka var1, boolean var2) {
      return new zzalh(var1, var2);
   }

   public final int zzhX() {
      return 16974374;
   }
}
