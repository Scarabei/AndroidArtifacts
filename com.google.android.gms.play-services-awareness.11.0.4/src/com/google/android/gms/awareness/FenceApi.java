package com.google.android.gms.awareness;

import com.google.android.gms.awareness.fence.FenceQueryRequest;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface FenceApi {
   PendingResult updateFences(GoogleApiClient var1, FenceUpdateRequest var2);

   PendingResult queryFences(GoogleApiClient var1, FenceQueryRequest var2);
}
