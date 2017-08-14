package com.google.android.gms.safetynet;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzcsa;
import com.google.android.gms.internal.zzcso;

public final class SafetyNet {
   private static com.google.android.gms.common.api.Api.zzf zzajR = new com.google.android.gms.common.api.Api.zzf();
   private static com.google.android.gms.common.api.Api.zza zzajS = new zzi();
   /** @deprecated */
   @Deprecated
   public static final Api API;
   /** @deprecated */
   @Deprecated
   public static final SafetyNetApi SafetyNetApi;
   private static zzm zzbBL;

   public static SafetyNetClient getClient(Context var0) {
      return new SafetyNetClient(var0);
   }

   public static SafetyNetClient getClient(Activity var0) {
      return new SafetyNetClient(var0);
   }

   static {
      API = new Api("SafetyNet.API", zzajS, zzajR);
      SafetyNetApi = new zzcsa();
      zzbBL = new zzcso();
   }
}
