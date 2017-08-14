package com.google.android.gms.instantapps;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzcbk;
import com.google.android.gms.internal.zzcbn;
import com.google.android.gms.internal.zzcbr;

public final class InstantApps {
   private static final zzf zzajR = new zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zza();
   public static final Api API;
   public static final InstantAppsApi InstantAppsApi;

   public static PackageManagerCompat getPackageManagerCompat(Context var0) {
      return zzcbr.zzi(var0, true);
   }

   public static ActivityCompat getActivityCompat(Activity var0) {
      return new zzcbk(var0);
   }

   /** @deprecated */
   @Deprecated
   public static PackageManagerWrapper getPackageManager(Context var0, boolean var1) {
      return zzcbr.zzi(var0, var1);
   }

   static {
      API = new Api("InstantApps.API", zzajS, zzajR);
      InstantAppsApi = new zzcbn();
   }
}
