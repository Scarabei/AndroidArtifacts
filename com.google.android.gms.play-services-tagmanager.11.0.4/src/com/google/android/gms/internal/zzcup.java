package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzg;

final class zzcup {
   public static void zzc(String var0, Context var1) {
      zzcvk.e(var0);
      if (zzg.zza(var1, new RuntimeException(var0))) {
         zzcvk.v("Crash reported successfully.");
      } else {
         zzcvk.v("Failed to report crash");
      }
   }

   public static void zzd(String var0, Context var1) {
      zzcvk.zzaT(var0);
      if (zzg.zza(var1, new RuntimeException(var0))) {
         zzcvk.v("Crash reported successfully.");
      } else {
         zzcvk.v("Failed to report crash");
      }
   }

   public static void zza(String var0, Throwable var1, Context var2) {
      zzcvk.zzb(var0, var1);
      if (zzg.zza(var2, var1)) {
         zzcvk.v("Crash reported successfully.");
      } else {
         zzcvk.v("Failed to report crash");
      }
   }
}
