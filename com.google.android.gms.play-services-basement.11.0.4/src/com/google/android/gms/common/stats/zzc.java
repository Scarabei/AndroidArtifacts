package com.google.android.gms.common.stats;

import android.os.Process;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;

public final class zzc {
   public static String zza(WakeLock var0, String var1) {
      String var10000 = String.valueOf(String.valueOf((long)Process.myPid() << 32 | (long)System.identityHashCode(var0)));
      String var10001 = String.valueOf(TextUtils.isEmpty(var1) ? "" : var1);
      return var10001.length() != 0 ? var10000.concat(var10001) : new String(var10000);
   }
}
