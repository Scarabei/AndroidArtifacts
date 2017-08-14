package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements;

final class zzn implements Achievements.UpdateAchievementResult {
   // $FF: synthetic field
   private Status zzakB;
   // $FF: synthetic field
   private zzm zzbaR;

   zzn(zzm var1, Status var2) {
      this.zzbaR = var1;
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final String getAchievementId() {
      return zzm.zza(this.zzbaR);
   }
}
