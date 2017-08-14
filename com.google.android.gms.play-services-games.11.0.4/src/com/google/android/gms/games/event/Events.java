package com.google.android.gms.games.event;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public interface Events {
   PendingResult load(GoogleApiClient var1, boolean var2);

   PendingResult loadByIds(GoogleApiClient var1, boolean var2, String... var3);

   void increment(GoogleApiClient var1, String var2, int var3);

   public interface LoadEventsResult extends Releasable, Result {
      EventBuffer getEvents();
   }
}
