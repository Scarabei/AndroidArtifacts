package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzbha;

public final class zzd {
   public static int zzA(Context var0, String var1) {
      PackageInfo var2;
      if ((var2 = zzB(var0, var1)) != null && var2.applicationInfo != null) {
         Bundle var3 = var2.applicationInfo.metaData;
         return var2.applicationInfo.metaData == null ? -1 : var3.getInt("com.google.android.gms.version", -1);
      } else {
         return -1;
      }
   }

   @Nullable
   private static PackageInfo zzB(Context var0, String var1) {
      try {
         PackageInfo var2 = zzbha.zzaP(var0).getPackageInfo(var1, 128);
         return var2;
      } catch (NameNotFoundException var3) {
         return null;
      }
   }

   public static boolean zzC(Context var0, String var1) {
      "com.google.android.gms".equals(var1);

      try {
         return (zzbha.zzaP(var0).getApplicationInfo(var1, 0).flags & 2097152) != 0;
      } catch (NameNotFoundException var2) {
         return false;
      }
   }
}
