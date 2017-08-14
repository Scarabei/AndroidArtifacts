package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class zzbue {
   private static final List zzaTh;

   public static long zza(long var0, TimeUnit var2, TimeUnit var3) {
      return var2.convert(var3.convert(var0, var2), var3);
   }

   public static boolean zza(TimeUnit var0) {
      TimeUnit var2 = TimeUnit.MILLISECONDS;
      return zzaTh.indexOf(var0) < zzaTh.indexOf(var2);
   }

   static {
      zzaTh = Arrays.asList(TimeUnit.NANOSECONDS, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS);
   }
}
