package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

final class zzau implements Leaderboards.SubmitScoreResult {
   // $FF: synthetic field
   private Status zzakB;

   zzau(zzat var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final ScoreSubmissionData getScoreData() {
      return new ScoreSubmissionData(DataHolder.zzau(14));
   }
}
