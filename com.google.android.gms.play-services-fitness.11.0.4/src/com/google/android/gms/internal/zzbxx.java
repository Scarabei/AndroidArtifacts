package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.GoalsApi;
import com.google.android.gms.fitness.request.GoalsReadRequest;

public final class zzbxx implements GoalsApi {
   public final PendingResult readCurrentGoals(GoogleApiClient var1, GoalsReadRequest var2) {
      return var1.zzd(new zzbxy(this, var1, var2));
   }
}
