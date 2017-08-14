package com.google.android.gms.internal;

import com.google.android.gms.awareness.FenceApi;
import com.google.android.gms.awareness.SnapshotApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;

public final class zzbil {
   private static zzf zzajR = new zzf();
   public static final FenceApi FenceApi = new zzbip();
   public static final SnapshotApi SnapshotApi = new zzatj();
   private static final zza zzajS = new zzbim();
   public static final Api API;

   static {
      API = new Api("ContextManager.API", zzajS, zzajR);
   }
}
