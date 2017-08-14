package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.internal.zzbdw;
import java.util.ArrayList;
import java.util.List;

public final class zzbt implements Requests {
   public final void registerRequestListener(GoogleApiClient var1, OnRequestReceivedListener var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         zzbdw var4 = var1.zzp(var2);
         var3.zzd(var4);
      }

   }

   public final void unregisterRequestListener(GoogleApiClient var1) {
      GamesClientImpl var2;
      if ((var2 = Games.zza(var1, false)) != null) {
         var2.zzuC();
      }

   }

   public final Intent getInboxIntent(GoogleApiClient var1) {
      return Games.zzf(var1).zzuH();
   }

   public final Intent getSendIntent(GoogleApiClient var1, int var2, byte[] var3, int var4, Bitmap var5, String var6) {
      return Games.zzf(var1).zza(var2, var3, var4, var5, var6);
   }

   public final int getMaxPayloadSize(GoogleApiClient var1) {
      return Games.zzf(var1).zzuI();
   }

   public final int getMaxLifetimeDays(GoogleApiClient var1) {
      return Games.zzf(var1).zzuJ();
   }

   public final PendingResult acceptRequest(GoogleApiClient var1, String var2) {
      ArrayList var3;
      (var3 = new ArrayList()).add(var2);
      return this.acceptRequests(var1, var3);
   }

   public final PendingResult acceptRequests(GoogleApiClient var1, List var2) {
      String[] var3 = var2 == null ? null : (String[])var2.toArray(new String[var2.size()]);
      return var1.zze(new zzbu(this, var1, var3));
   }

   public final PendingResult dismissRequest(GoogleApiClient var1, String var2) {
      ArrayList var3;
      (var3 = new ArrayList()).add(var2);
      return this.dismissRequests(var1, var3);
   }

   public final PendingResult dismissRequests(GoogleApiClient var1, List var2) {
      String[] var3 = var2 == null ? null : (String[])var2.toArray(new String[var2.size()]);
      return var1.zze(new zzbv(this, var1, var3));
   }

   public final PendingResult loadRequests(GoogleApiClient var1, int var2, int var3, int var4) {
      return var1.zzd(new zzbw(this, var1, var2, var3, var4));
   }

   public final ArrayList getGameRequestsFromInboxResponse(Intent var1) {
      return var1 == null ? new ArrayList() : this.getGameRequestsFromBundle(var1.getExtras());
   }

   public final ArrayList getGameRequestsFromBundle(Bundle var1) {
      if (var1 != null && var1.containsKey("requests")) {
         ArrayList var2 = (ArrayList)var1.get("requests");
         ArrayList var3 = new ArrayList();
         int var4 = 0;

         for(int var5 = var2.size(); var4 < var5; ++var4) {
            var3.add((GameRequest)var2.get(var4));
         }

         return var3;
      } else {
         return new ArrayList();
      }
   }
}
