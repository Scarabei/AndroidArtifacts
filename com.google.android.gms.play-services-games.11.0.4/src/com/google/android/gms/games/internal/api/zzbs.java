package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.internal.zzbdw;
import java.util.List;

public final class zzbs implements RealTimeMultiplayer {
   public final Intent getWaitingRoomIntent(GoogleApiClient var1, Room var2, int var3) {
      return Games.zzf(var1).zza(var2, var3);
   }

   public final Intent getSelectOpponentsIntent(GoogleApiClient var1, int var2, int var3) {
      return Games.zzf(var1).zzc(var2, var3, true);
   }

   public final Intent getSelectOpponentsIntent(GoogleApiClient var1, int var2, int var3, boolean var4) {
      return Games.zzf(var1).zzc(var2, var3, var4);
   }

   public final void create(GoogleApiClient var1, RoomConfig var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         zzbdw var4 = var1.zzp(var2.getRoomUpdateListener());
         zzbdw var5 = zza(var1, var2.getRoomStatusUpdateListener());
         zzbdw var6 = zza(var1, var2.getMessageReceivedListener());
         var3.zza(var4, var5, var6, var2);
      }
   }

   public final void join(GoogleApiClient var1, RoomConfig var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         zzbdw var4 = var1.zzp(var2.getRoomUpdateListener());
         zzbdw var5 = zza(var1, var2.getRoomStatusUpdateListener());
         zzbdw var6 = zza(var1, var2.getMessageReceivedListener());
         var3.zzb(var4, var5, var6, var2);
      }
   }

   public final void leave(GoogleApiClient var1, RoomUpdateListener var2, String var3) {
      GamesClientImpl var4;
      if ((var4 = Games.zza(var1, false)) != null) {
         zzbdw var5 = var1.zzp(var2);
         var4.zza(var5, var3);
      }

   }

   public final int sendReliableMessage(GoogleApiClient var1, RealTimeMultiplayer.ReliableMessageSentCallback var2, byte[] var3, String var4, String var5) {
      zzbdw var6 = zza(var1, var2);
      return Games.zzf(var1).zza(var6, var3, var4, var5);
   }

   public final int sendUnreliableMessage(GoogleApiClient var1, byte[] var2, String var3, String var4) {
      return Games.zzf(var1).zza(var2, var3, new String[]{var4});
   }

   public final int sendUnreliableMessage(GoogleApiClient var1, byte[] var2, String var3, List var4) {
      String[] var5 = (String[])var4.toArray(new String[var4.size()]);
      return Games.zzf(var1).zza(var2, var3, var5);
   }

   public final int sendUnreliableMessageToOthers(GoogleApiClient var1, byte[] var2, String var3) {
      return Games.zzf(var1).zzc(var2, var3);
   }

   public final void declineInvitation(GoogleApiClient var1, String var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         var3.zzp(var2, 0);
      }

   }

   public final void dismissInvitation(GoogleApiClient var1, String var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         var3.zzo(var2, 0);
      }

   }

   private static zzbdw zza(GoogleApiClient var0, Object var1) {
      return var1 == null ? null : var0.zzp(var1);
   }
}
