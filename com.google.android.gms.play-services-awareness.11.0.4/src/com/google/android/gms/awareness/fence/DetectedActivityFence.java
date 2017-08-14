package com.google.android.gms.awareness.fence;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.internal.zzbin;
import com.google.android.gms.internal.zzbiy;

public final class DetectedActivityFence {
   public static final int IN_VEHICLE = 0;
   public static final int ON_BICYCLE = 1;
   public static final int ON_FOOT = 2;
   public static final int STILL = 3;
   public static final int UNKNOWN = 4;
   public static final int WALKING = 7;
   public static final int RUNNING = 8;

   @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
   public static AwarenessFence during(int... var0) {
      return zzbiy.zza(zzbin.zza(1, var0));
   }

   @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
   public static AwarenessFence starting(int... var0) {
      return zzbiy.zza(zzbin.zza(2, var0));
   }

   @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
   public static AwarenessFence stopping(int... var0) {
      return zzbiy.zza(zzbin.zza(3, var0));
   }
}
