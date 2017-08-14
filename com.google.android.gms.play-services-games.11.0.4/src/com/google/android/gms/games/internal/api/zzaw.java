package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class zzaw implements Notifications {
   public final void clearAll(GoogleApiClient var1) {
      this.clear(var1, 63);
   }

   public final void clear(GoogleApiClient var1, int var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         var3.zzba(var2);
      }

   }
}
