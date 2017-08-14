package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievements;

public final class zza implements Achievements {
   public final Intent getAchievementsIntent(GoogleApiClient var1) {
      return Games.zzf(var1).zzuw();
   }

   public final PendingResult load(GoogleApiClient var1, boolean var2) {
      return var1.zzd(new zzb(this, var1, var2));
   }

   public final void reveal(GoogleApiClient var1, String var2) {
      var1.zze(new zzc(this, var2, var1, var2));
   }

   public final PendingResult revealImmediate(GoogleApiClient var1, String var2) {
      return var1.zze(new zzd(this, var2, var1, var2));
   }

   public final void unlock(GoogleApiClient var1, String var2) {
      var1.zze(new zze(this, var2, var1, var2));
   }

   public final PendingResult unlockImmediate(GoogleApiClient var1, String var2) {
      return var1.zze(new zzf(this, var2, var1, var2));
   }

   public final void increment(GoogleApiClient var1, String var2, int var3) {
      var1.zze(new zzg(this, var2, var1, var2, var3));
   }

   public final PendingResult incrementImmediate(GoogleApiClient var1, String var2, int var3) {
      return var1.zze(new zzh(this, var2, var1, var2, var3));
   }

   public final void setSteps(GoogleApiClient var1, String var2, int var3) {
      var1.zze(new zzi(this, var2, var1, var2, var3));
   }

   public final PendingResult setStepsImmediate(GoogleApiClient var1, String var2, int var3) {
      return var1.zze(new zzj(this, var2, var1, var2, var3));
   }
}
