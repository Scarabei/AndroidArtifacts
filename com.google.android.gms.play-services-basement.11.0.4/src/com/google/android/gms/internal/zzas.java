package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import java.io.File;

public final class zzas {
   public static zzs zza(Context var0, zzan var1) {
      File var2 = new File(var0.getCacheDir(), "volley");
      String var3 = "volley/0";

      try {
         String var4 = var0.getPackageName();
         int var6 = var0.getPackageManager().getPackageInfo(var4, 0).versionCode;
         var3 = (new StringBuilder(12 + String.valueOf(var4).length())).append(var4).append("/").append(var6).toString();
      } catch (NameNotFoundException var7) {
         ;
      }

      Object var8;
      if (VERSION.SDK_INT >= 9) {
         var8 = new zzao();
      } else {
         var8 = new zzak(AndroidHttpClient.newInstance(var3));
      }

      zzad var9 = new zzad((zzan)var8);
      zzs var5;
      (var5 = new zzs(new zzag(var2), var9)).start();
      return var5;
   }
}
