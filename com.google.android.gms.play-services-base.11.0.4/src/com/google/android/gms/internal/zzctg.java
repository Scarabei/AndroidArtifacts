package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;

public final class zzctg {
   private static Api.zzf zzajR = new Api.zzf();
   private static Api.zzf zzbCJ = new Api.zzf();
   public static final Api.zza zzajS = new zzcth();
   private static Api.zza zzbCK = new zzcti();
   private static Scope zzalV = new Scope("profile");
   private static Scope zzalW = new Scope("email");
   public static final Api API;
   private static Api zzaMc;

   static {
      API = new Api("SignIn.API", zzajS, zzajR);
      zzaMc = new Api("SignIn.INTERNAL_API", zzbCK, zzbCJ);
   }
}
