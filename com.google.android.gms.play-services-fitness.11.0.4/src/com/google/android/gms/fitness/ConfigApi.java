package com.google.android.gms.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;

public interface ConfigApi {
   PendingResult createCustomDataType(GoogleApiClient var1, DataTypeCreateRequest var2);

   PendingResult readDataType(GoogleApiClient var1, String var2);

   PendingResult disableFit(GoogleApiClient var1);
}
