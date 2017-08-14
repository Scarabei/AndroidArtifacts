package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface CastRemoteDisplayApi {
   PendingResult startRemoteDisplay(GoogleApiClient var1, String var2);

   PendingResult stopRemoteDisplay(GoogleApiClient var1);
}
