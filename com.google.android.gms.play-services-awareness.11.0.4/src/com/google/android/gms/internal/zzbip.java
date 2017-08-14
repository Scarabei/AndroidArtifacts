package com.google.android.gms.internal;

import com.google.android.gms.awareness.FenceApi;
import com.google.android.gms.awareness.fence.FenceQueryRequest;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public final class zzbip implements FenceApi {
   public final PendingResult updateFences(GoogleApiClient var1, FenceUpdateRequest var2) {
      return var1.zzd(new zzbiq(this, var1, var2));
   }

   public final PendingResult queryFences(GoogleApiClient var1, FenceQueryRequest var2) {
      return var1.zzd(new zzbir(this, var1, var2));
   }
}
