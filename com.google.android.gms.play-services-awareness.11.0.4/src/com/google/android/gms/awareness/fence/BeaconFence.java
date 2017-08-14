package com.google.android.gms.awareness.fence;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbis;
import com.google.android.gms.internal.zzbiy;
import java.util.Collection;

public final class BeaconFence {
   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence found(BeaconState.TypeFilter... var0) {
      zzbo.zzaf(var0 != null && var0.length > 0);
      return zzbiy.zza(zzbis.zza(var0));
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence lost(BeaconState.TypeFilter... var0) {
      zzbo.zzaf(var0 != null && var0.length > 0);
      return zzbiy.zza(zzbis.zzb(var0));
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence near(BeaconState.TypeFilter... var0) {
      zzbo.zzaf(var0 != null && var0.length > 0);
      return zzbiy.zza(zzbis.zzc(var0));
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence found(Collection var0) {
      zzbo.zzaf(var0 != null && !var0.isEmpty());
      return found((BeaconState.TypeFilter[])var0.toArray(new BeaconState.TypeFilter[var0.size()]));
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence lost(Collection var0) {
      zzbo.zzaf(var0 != null && !var0.isEmpty());
      return lost((BeaconState.TypeFilter[])var0.toArray(new BeaconState.TypeFilter[var0.size()]));
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence near(Collection var0) {
      zzbo.zzaf(var0 != null && !var0.isEmpty());
      return near((BeaconState.TypeFilter[])var0.toArray(new BeaconState.TypeFilter[var0.size()]));
   }
}
