package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.ConfigApi;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;

public final class zzbxs implements ConfigApi {
   public final PendingResult createCustomDataType(GoogleApiClient var1, DataTypeCreateRequest var2) {
      return var1.zze(new zzbxt(this, var1, var2));
   }

   public final PendingResult readDataType(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzbxu(this, var1, var2));
   }

   public final PendingResult disableFit(GoogleApiClient var1) {
      return var1.zze(new zzbxv(this, var1));
   }
}
