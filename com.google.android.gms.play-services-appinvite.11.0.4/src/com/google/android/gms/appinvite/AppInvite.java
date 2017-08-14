package com.google.android.gms.appinvite;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzapy;

/** @deprecated */
@Deprecated
public final class AppInvite {
   private static zzf zzajR = new zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zza();
   public static final Api API;
   public static final AppInviteApi AppInviteApi;

   static {
      API = new Api("AppInvite.API", zzajS, zzajR);
      AppInviteApi = new zzapy();
   }
}
