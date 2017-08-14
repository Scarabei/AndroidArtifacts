package com.google.android.gms.games.achievement;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public interface Achievements {
   Intent getAchievementsIntent(GoogleApiClient var1);

   PendingResult load(GoogleApiClient var1, boolean var2);

   void reveal(GoogleApiClient var1, String var2);

   PendingResult revealImmediate(GoogleApiClient var1, String var2);

   void unlock(GoogleApiClient var1, String var2);

   PendingResult unlockImmediate(GoogleApiClient var1, String var2);

   void increment(GoogleApiClient var1, String var2, int var3);

   PendingResult incrementImmediate(GoogleApiClient var1, String var2, int var3);

   void setSteps(GoogleApiClient var1, String var2, int var3);

   PendingResult setStepsImmediate(GoogleApiClient var1, String var2, int var3);

   public interface UpdateAchievementResult extends Result {
      String getAchievementId();
   }

   public interface LoadAchievementsResult extends Releasable, Result {
      AchievementBuffer getAchievements();
   }
}
