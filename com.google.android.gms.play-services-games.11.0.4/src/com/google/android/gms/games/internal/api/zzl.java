package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;

final class zzl implements Achievements.LoadAchievementsResult {
   // $FF: synthetic field
   private Status zzakB;

   zzl(zzk var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final AchievementBuffer getAchievements() {
      return new AchievementBuffer(DataHolder.zzau(14));
   }
}
