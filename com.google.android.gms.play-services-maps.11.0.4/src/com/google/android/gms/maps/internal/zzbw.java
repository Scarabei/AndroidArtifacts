package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;

public final class zzbw {
   public static void zzd(Bundle var0, Bundle var1) {
      if (var0 != null && var1 != null) {
         Parcelable var2;
         if ((var2 = zzg(var0, "MapOptions")) != null) {
            zza(var1, "MapOptions", var2);
         }

         Parcelable var3;
         if ((var3 = zzg(var0, "StreetViewPanoramaOptions")) != null) {
            zza(var1, "StreetViewPanoramaOptions", var3);
         }

         Parcelable var4;
         if ((var4 = zzg(var0, "camera")) != null) {
            zza(var1, "camera", var4);
         }

         if (var0.containsKey("position")) {
            var1.putString("position", var0.getString("position"));
         }

         if (var0.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
            var1.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", var0.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
         }

      }
   }

   private static Parcelable zzg(Bundle var0, String var1) {
      if (var0 == null) {
         return null;
      } else {
         var0.setClassLoader(zzbw.class.getClassLoader());
         Bundle var2;
         if ((var2 = var0.getBundle("map_state")) == null) {
            return null;
         } else {
            ClassLoader var3 = zzbw.class.getClassLoader();
            var2.setClassLoader(var3);
            return var2.getParcelable(var1);
         }
      }
   }

   public static void zza(Bundle var0, String var1, Parcelable var2) {
      var0.setClassLoader(zzbw.class.getClassLoader());
      Bundle var3;
      if ((var3 = var0.getBundle("map_state")) == null) {
         var3 = new Bundle();
      }

      var3.setClassLoader(zzbw.class.getClassLoader());
      var3.putParcelable(var1, var2);
      var0.putBundle("map_state", var3);
   }
}
