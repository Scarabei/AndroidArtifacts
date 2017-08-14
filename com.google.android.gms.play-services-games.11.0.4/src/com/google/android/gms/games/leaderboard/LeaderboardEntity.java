package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;
import java.util.Arrays;

public final class LeaderboardEntity implements Leaderboard {
   private final String zzbcL;
   private final String zzalP;
   private final Uri zzaXU;
   private final int zzbcM;
   private final ArrayList zzbcN;
   private final Game zzbcO;
   private final String zzaYf;

   public LeaderboardEntity(Leaderboard var1) {
      this.zzbcL = var1.getLeaderboardId();
      this.zzalP = var1.getDisplayName();
      this.zzaXU = var1.getIconImageUri();
      this.zzaYf = var1.getIconImageUrl();
      this.zzbcM = var1.getScoreOrder();
      Game var2 = var1.getGame();
      this.zzbcO = var2 == null ? null : new GameEntity(var2);
      ArrayList var3;
      int var4 = (var3 = var1.getVariants()).size();
      this.zzbcN = new ArrayList(var4);

      for(int var5 = 0; var5 < var4; ++var5) {
         this.zzbcN.add((zzb)((LeaderboardVariant)var3.get(var5)).freeze());
      }

   }

   public final String getLeaderboardId() {
      return this.zzbcL;
   }

   public final String getDisplayName() {
      return this.zzalP;
   }

   public final void getDisplayName(CharArrayBuffer var1) {
      zzh.zzb(this.zzalP, var1);
   }

   public final Uri getIconImageUri() {
      return this.zzaXU;
   }

   public final String getIconImageUrl() {
      return this.zzaYf;
   }

   public final int getScoreOrder() {
      return this.zzbcM;
   }

   public final ArrayList getVariants() {
      return new ArrayList(this.zzbcN);
   }

   public final Game getGame() {
      return this.zzbcO;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(Leaderboard var0) {
      return Arrays.hashCode(new Object[]{var0.getLeaderboardId(), var0.getDisplayName(), var0.getIconImageUri(), var0.getScoreOrder(), var0.getVariants()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(Leaderboard var0, Object var1) {
      if (!(var1 instanceof Leaderboard)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         Leaderboard var2;
         return zzbe.equal((var2 = (Leaderboard)var1).getLeaderboardId(), var0.getLeaderboardId()) && zzbe.equal(var2.getDisplayName(), var0.getDisplayName()) && zzbe.equal(var2.getIconImageUri(), var0.getIconImageUri()) && zzbe.equal(var2.getScoreOrder(), var0.getScoreOrder()) && zzbe.equal(var2.getVariants(), var0.getVariants());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(Leaderboard var0) {
      return zzbe.zzt(var0).zzg("LeaderboardId", var0.getLeaderboardId()).zzg("DisplayName", var0.getDisplayName()).zzg("IconImageUri", var0.getIconImageUri()).zzg("IconImageUrl", var0.getIconImageUrl()).zzg("ScoreOrder", var0.getScoreOrder()).zzg("Variants", var0.getVariants()).toString();
   }
}
