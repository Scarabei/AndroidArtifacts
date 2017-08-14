package com.google.android.gms.internal;

import android.os.Build.VERSION;

public final class zzanr {
   public static int version() {
      int var0;
      try {
         var0 = Integer.parseInt(VERSION.SDK);
      } catch (NumberFormatException var1) {
         zzaob.zzf("Invalid version number", VERSION.SDK);
         var0 = 0;
      }

      return var0;
   }
}
