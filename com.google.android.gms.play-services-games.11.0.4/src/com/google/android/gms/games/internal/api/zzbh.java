package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.internal.zzbdw;

public final class zzbh implements Quests {
   public final Intent getQuestsIntent(GoogleApiClient var1, int[] var2) {
      return Games.zzf(var1).zzb(var2);
   }

   public final Intent getQuestIntent(GoogleApiClient var1, String var2) {
      return Games.zzf(var1).zzdk(var2);
   }

   public final void registerQuestUpdateListener(GoogleApiClient var1, QuestUpdateListener var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         zzbdw var4 = var1.zzp(var2);
         var3.zzc(var4);
      }

   }

   public final void unregisterQuestUpdateListener(GoogleApiClient var1) {
      GamesClientImpl var2;
      if ((var2 = Games.zza(var1, false)) != null) {
         var2.zzuB();
      }

   }

   public final PendingResult accept(GoogleApiClient var1, String var2) {
      return var1.zze(new zzbi(this, var1, var2));
   }

   public final PendingResult claim(GoogleApiClient var1, String var2, String var3) {
      return var1.zze(new zzbj(this, var1, var2, var3));
   }

   public final PendingResult load(GoogleApiClient var1, int[] var2, int var3, boolean var4) {
      return var1.zzd(new zzbk(this, var1, var2, var3, var4));
   }

   public final PendingResult loadByIds(GoogleApiClient var1, boolean var2, String... var3) {
      return var1.zzd(new zzbl(this, var1, var2, var3));
   }

   public final void showStateChangedPopup(GoogleApiClient var1, String var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         var3.zzdl(var2);
      }

   }
}
