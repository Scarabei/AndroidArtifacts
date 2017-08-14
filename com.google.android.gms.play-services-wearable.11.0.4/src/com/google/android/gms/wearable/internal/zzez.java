package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;

public final class zzez {
   public static IntentFilter zzgl(String var0) {
      IntentFilter var1;
      (var1 = new IntentFilter(var0)).addDataScheme("wear");
      var1.addDataAuthority("*", (String)null);
      return var1;
   }

   public static IntentFilter zza(String var0, Uri var1, int var2) {
      IntentFilter var3 = new IntentFilter(var0);
      if (var1.getScheme() != null) {
         var3.addDataScheme(var1.getScheme());
      }

      if (var1.getAuthority() != null) {
         var3.addDataAuthority(var1.getAuthority(), Integer.toString(var1.getPort()));
      }

      if (var1.getPath() != null) {
         var3.addDataPath(var1.getPath(), var2);
      }

      return var3;
   }
}
