package com.google.android.gms.auth.account;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzaqn;

public class WorkAccount {
   private static final com.google.android.gms.common.api.Api.zzf zzajR = new com.google.android.gms.common.api.Api.zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zzg();
   public static final Api API;
   public static final WorkAccountApi WorkAccountApi;

   static {
      API = new Api("WorkAccount.API", zzajS, zzajR);
      WorkAccountApi = new zzaqn();
   }
}
