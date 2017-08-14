package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.internal.zzbdw;

public final class zzab implements Invitations {
   public final Intent getInvitationInboxIntent(GoogleApiClient var1) {
      return Games.zzf(var1).zzuy();
   }

   public final void registerInvitationListener(GoogleApiClient var1, OnInvitationReceivedListener var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         zzbdw var4 = var1.zzp(var2);
         var3.zza(var4);
      }

   }

   public final void unregisterInvitationListener(GoogleApiClient var1) {
      GamesClientImpl var2;
      if ((var2 = Games.zza(var1, false)) != null) {
         var2.zzuz();
      }

   }

   public final PendingResult loadInvitations(GoogleApiClient var1) {
      return this.loadInvitations(var1, 0);
   }

   public final PendingResult loadInvitations(GoogleApiClient var1, int var2) {
      return var1.zzd(new zzac(this, var1, var2));
   }
}
