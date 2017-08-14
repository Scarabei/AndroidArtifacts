package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import java.util.Arrays;

public final class LeaderboardScoreEntity implements LeaderboardScore {
   private final long zzbcR;
   private final String zzbcS;
   private final String zzbcT;
   private final long zzbcU;
   private final long zzbcV;
   private final String zzbcW;
   private final Uri zzbcX;
   private final Uri zzbcY;
   private final PlayerEntity zzbcZ;
   private final String zzbda;
   private final String zzbdb;
   private final String zzbdc;

   public LeaderboardScoreEntity(LeaderboardScore var1) {
      this.zzbcR = var1.getRank();
      this.zzbcS = (String)zzbo.zzu(var1.getDisplayRank());
      this.zzbcT = (String)zzbo.zzu(var1.getDisplayScore());
      this.zzbcU = var1.getRawScore();
      this.zzbcV = var1.getTimestampMillis();
      this.zzbcW = var1.getScoreHolderDisplayName();
      this.zzbcX = var1.getScoreHolderIconImageUri();
      this.zzbcY = var1.getScoreHolderHiResImageUri();
      Player var2 = var1.getScoreHolder();
      this.zzbcZ = var2 == null ? null : (PlayerEntity)var2.freeze();
      this.zzbda = var1.getScoreTag();
      this.zzbdb = var1.getScoreHolderIconImageUrl();
      this.zzbdc = var1.getScoreHolderHiResImageUrl();
   }

   public final long getRank() {
      return this.zzbcR;
   }

   public final String getDisplayRank() {
      return this.zzbcS;
   }

   public final void getDisplayRank(CharArrayBuffer var1) {
      zzh.zzb(this.zzbcS, var1);
   }

   public final String getDisplayScore() {
      return this.zzbcT;
   }

   public final void getDisplayScore(CharArrayBuffer var1) {
      zzh.zzb(this.zzbcT, var1);
   }

   public final long getRawScore() {
      return this.zzbcU;
   }

   public final long getTimestampMillis() {
      return this.zzbcV;
   }

   public final String getScoreHolderDisplayName() {
      return this.zzbcZ == null ? this.zzbcW : this.zzbcZ.getDisplayName();
   }

   public final void getScoreHolderDisplayName(CharArrayBuffer var1) {
      if (this.zzbcZ == null) {
         zzh.zzb(this.zzbcW, var1);
      } else {
         this.zzbcZ.getDisplayName(var1);
      }
   }

   public final Uri getScoreHolderIconImageUri() {
      return this.zzbcZ == null ? this.zzbcX : this.zzbcZ.getIconImageUri();
   }

   public final String getScoreHolderIconImageUrl() {
      return this.zzbcZ == null ? this.zzbdb : this.zzbcZ.getIconImageUrl();
   }

   public final Uri getScoreHolderHiResImageUri() {
      return this.zzbcZ == null ? this.zzbcY : this.zzbcZ.getHiResImageUri();
   }

   public final String getScoreHolderHiResImageUrl() {
      return this.zzbcZ == null ? this.zzbdc : this.zzbcZ.getHiResImageUrl();
   }

   public final Player getScoreHolder() {
      return this.zzbcZ;
   }

   public final String getScoreTag() {
      return this.zzbda;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(LeaderboardScore var0) {
      return Arrays.hashCode(new Object[]{var0.getRank(), var0.getDisplayRank(), var0.getRawScore(), var0.getDisplayScore(), var0.getTimestampMillis(), var0.getScoreHolderDisplayName(), var0.getScoreHolderIconImageUri(), var0.getScoreHolderHiResImageUri(), var0.getScoreHolder()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(LeaderboardScore var0, Object var1) {
      if (!(var1 instanceof LeaderboardScore)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         LeaderboardScore var2;
         return zzbe.equal((var2 = (LeaderboardScore)var1).getRank(), var0.getRank()) && zzbe.equal(var2.getDisplayRank(), var0.getDisplayRank()) && zzbe.equal(var2.getRawScore(), var0.getRawScore()) && zzbe.equal(var2.getDisplayScore(), var0.getDisplayScore()) && zzbe.equal(var2.getTimestampMillis(), var0.getTimestampMillis()) && zzbe.equal(var2.getScoreHolderDisplayName(), var0.getScoreHolderDisplayName()) && zzbe.equal(var2.getScoreHolderIconImageUri(), var0.getScoreHolderIconImageUri()) && zzbe.equal(var2.getScoreHolderHiResImageUri(), var0.getScoreHolderHiResImageUri()) && zzbe.equal(var2.getScoreHolder(), var0.getScoreHolder()) && zzbe.equal(var2.getScoreTag(), var0.getScoreTag());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(LeaderboardScore var0) {
      return zzbe.zzt(var0).zzg("Rank", var0.getRank()).zzg("DisplayRank", var0.getDisplayRank()).zzg("Score", var0.getRawScore()).zzg("DisplayScore", var0.getDisplayScore()).zzg("Timestamp", var0.getTimestampMillis()).zzg("DisplayName", var0.getScoreHolderDisplayName()).zzg("IconImageUri", var0.getScoreHolderIconImageUri()).zzg("IconImageUrl", var0.getScoreHolderIconImageUrl()).zzg("HiResImageUri", var0.getScoreHolderHiResImageUri()).zzg("HiResImageUrl", var0.getScoreHolderHiResImageUrl()).zzg("Player", var0.getScoreHolder() == null ? null : var0.getScoreHolder()).zzg("ScoreTag", var0.getScoreTag()).toString();
   }
}
