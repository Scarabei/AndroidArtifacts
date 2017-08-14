package com.google.android.gms.awareness;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzbil;

public final class Awareness {
   public static final FenceApi FenceApi;
   public static final SnapshotApi SnapshotApi;
   public static final Api API;

   static {
      FenceApi = zzbil.FenceApi;
      SnapshotApi = zzbil.SnapshotApi;
      API = zzbil.API;
   }
}
