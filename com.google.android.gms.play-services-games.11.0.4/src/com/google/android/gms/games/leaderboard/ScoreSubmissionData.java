package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzcai;
import java.util.HashMap;

public final class ScoreSubmissionData {
   private static final String[] zzbcJ = new String[]{"leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag"};
   private String zzbcL;
   private String zzaxn;
   private int zzaxu;
   private HashMap zzbdq;

   public ScoreSubmissionData(DataHolder var1) {
      this.zzaxu = var1.getStatusCode();
      this.zzbdq = new HashMap();
      int var2;
      zzbo.zzaf((var2 = var1.getCount()) == 3);

      for(int var3 = 0; var3 < var2; ++var3) {
         int var4 = var1.zzat(var3);
         if (var3 == 0) {
            this.zzbcL = var1.zzd("leaderboardId", var3, var4);
            this.zzaxn = var1.zzd("playerId", var3, var4);
         }

         if (var1.zze("hasResult", var3, var4)) {
            ScoreSubmissionData.Result var5 = new ScoreSubmissionData.Result(var1.zzb("rawScore", var3, var4), var1.zzd("formattedScore", var3, var4), var1.zzd("scoreTag", var3, var4), var1.zze("newBest", var3, var4));
            int var7 = var1.zzc("timeSpan", var3, var4);
            this.zzbdq.put(var7, var5);
         }
      }

   }

   public final String getLeaderboardId() {
      return this.zzbcL;
   }

   public final String getPlayerId() {
      return this.zzaxn;
   }

   public final ScoreSubmissionData.Result getScoreResult(int var1) {
      return (ScoreSubmissionData.Result)this.zzbdq.get(var1);
   }

   public final String toString() {
      zzbg var1 = zzbe.zzt(this).zzg("PlayerId", this.zzaxn).zzg("StatusCode", this.zzaxu);

      for(int var2 = 0; var2 < 3; ++var2) {
         ScoreSubmissionData.Result var3 = (ScoreSubmissionData.Result)this.zzbdq.get(var2);
         var1.zzg("TimesSpan", zzcai.zzbc(var2));
         var1.zzg("Result", var3 == null ? "null" : var3.toString());
      }

      return var1.toString();
   }

   public static final class Result {
      public final long rawScore;
      public final String formattedScore;
      public final String scoreTag;
      public final boolean newBest;

      public Result(long var1, String var3, String var4, boolean var5) {
         this.rawScore = var1;
         this.formattedScore = var3;
         this.scoreTag = var4;
         this.newBest = var5;
      }

      public final String toString() {
         return zzbe.zzt(this).zzg("RawScore", this.rawScore).zzg("FormattedScore", this.formattedScore).zzg("ScoreTag", this.scoreTag).zzg("NewBest", this.newBest).toString();
      }
   }
}
