package com.google.android.gms.awareness.fence;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.internal.zzbiy;
import com.google.android.gms.internal.zzbjo;

public final class LocationFence {
   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence in(double var0, double var2, double var4, long var6) {
      return zzbiy.zza(zzbjo.zza((int)(var0 * 1.0E7D), (int)(var2 * 1.0E7D), var4, var6));
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence entering(double var0, double var2, double var4) {
      return zzbiy.zza(zzbjo.zza((int)(var0 * 1.0E7D), (int)(var2 * 1.0E7D), var4));
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence exiting(double var0, double var2, double var4) {
      return zzbiy.zza(zzbjo.zzb((int)(var0 * 1.0E7D), (int)(var2 * 1.0E7D), var4));
   }
}
