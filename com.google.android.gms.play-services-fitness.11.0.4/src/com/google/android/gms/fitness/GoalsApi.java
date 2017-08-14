package com.google.android.gms.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.GoalsReadRequest;

public interface GoalsApi {
   PendingResult readCurrentGoals(GoogleApiClient var1, GoalsReadRequest var2);
}
