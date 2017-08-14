package com.google.android.gms.games.internal.api;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class zzp implements Events {
   public final PendingResult loadByIds(GoogleApiClient var1, boolean var2, String... var3) {
      return var1.zzd(new zzq(this, var1, var2, var3));
   }

   public final PendingResult load(GoogleApiClient var1, boolean var2) {
      return var1.zzd(new zzr(this, var1, var2));
   }

   @SuppressLint({"MissingRemoteException"})
   public final void increment(GoogleApiClient var1, String var2, int var3) {
      GamesClientImpl var4;
      if ((var4 = Games.zzb(var1, false)) != null) {
         if (var4.isConnected()) {
            var4.zzn(var2, var3);
         } else {
            var1.zze(new zzs(this, var1, var2, var3));
         }
      }
   }
}
