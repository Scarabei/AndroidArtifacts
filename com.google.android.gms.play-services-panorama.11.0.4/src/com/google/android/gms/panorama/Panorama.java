package com.google.android.gms.panorama;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzcqe;

public final class Panorama {
   public static final zzf zzajR = new zzf();
   private static com.google.android.gms.common.api.Api.zza zzajS = new zzb();
   public static final Api API;
   public static final PanoramaApi PanoramaApi;

   static {
      API = new Api("Panorama.API", zzajS, zzajR);
      PanoramaApi = new zzcqe();
   }
}
