package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzcai;
import java.util.Arrays;

public final class zzb implements LeaderboardVariant {
   private final int zzbde;
   private final int zzbdf;
   private final boolean zzbdg;
   private final long zzbdh;
   private final String zzbdi;
   private final long zzbdj;
   private final String zzbdk;
   private final String zzbdl;
   private final long zzbdm;
   private final String zzbdn;
   private final String zzbdo;
   private final String zzbdp;

   public zzb(LeaderboardVariant var1) {
      this.zzbde = var1.getTimeSpan();
      this.zzbdf = var1.getCollection();
      this.zzbdg = var1.hasPlayerInfo();
      this.zzbdh = var1.getRawPlayerScore();
      this.zzbdi = var1.getDisplayPlayerScore();
      this.zzbdj = var1.getPlayerRank();
      this.zzbdk = var1.getDisplayPlayerRank();
      this.zzbdl = var1.getPlayerScoreTag();
      this.zzbdm = var1.getNumScores();
      this.zzbdn = var1.zzvo();
      this.zzbdo = var1.zzvp();
      this.zzbdp = var1.zzvq();
   }

   public final int getTimeSpan() {
      return this.zzbde;
   }

   public final int getCollection() {
      return this.zzbdf;
   }

   public final boolean hasPlayerInfo() {
      return this.zzbdg;
   }

   public final long getRawPlayerScore() {
      return this.zzbdh;
   }

   public final String getDisplayPlayerScore() {
      return this.zzbdi;
   }

   public final long getPlayerRank() {
      return this.zzbdj;
   }

   public final String getDisplayPlayerRank() {
      return this.zzbdk;
   }

   public final String getPlayerScoreTag() {
      return this.zzbdl;
   }

   public final long getNumScores() {
      return this.zzbdm;
   }

   public final String zzvo() {
      return this.zzbdn;
   }

   public final String zzvp() {
      return this.zzbdo;
   }

   public final String zzvq() {
      return this.zzbdp;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(LeaderboardVariant var0) {
      return Arrays.hashCode(new Object[]{var0.getTimeSpan(), var0.getCollection(), var0.hasPlayerInfo(), var0.getRawPlayerScore(), var0.getDisplayPlayerScore(), var0.getPlayerRank(), var0.getDisplayPlayerRank(), var0.getNumScores(), var0.zzvo(), var0.zzvq(), var0.zzvp()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(LeaderboardVariant var0, Object var1) {
      if (!(var1 instanceof LeaderboardVariant)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         LeaderboardVariant var2;
         return zzbe.equal((var2 = (LeaderboardVariant)var1).getTimeSpan(), var0.getTimeSpan()) && zzbe.equal(var2.getCollection(), var0.getCollection()) && zzbe.equal(var2.hasPlayerInfo(), var0.hasPlayerInfo()) && zzbe.equal(var2.getRawPlayerScore(), var0.getRawPlayerScore()) && zzbe.equal(var2.getDisplayPlayerScore(), var0.getDisplayPlayerScore()) && zzbe.equal(var2.getPlayerRank(), var0.getPlayerRank()) && zzbe.equal(var2.getDisplayPlayerRank(), var0.getDisplayPlayerRank()) && zzbe.equal(var2.getNumScores(), var0.getNumScores()) && zzbe.equal(var2.zzvo(), var0.zzvo()) && zzbe.equal(var2.zzvq(), var0.zzvq()) && zzbe.equal(var2.zzvp(), var0.zzvp());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(LeaderboardVariant var0) {
      zzbg var10000 = zzbe.zzt(var0).zzg("TimeSpan", zzcai.zzbc(var0.getTimeSpan()));
      int var1;
      String var10002;
      switch(var1 = var0.getCollection()) {
      case -1:
         var10002 = "UNKNOWN";
         break;
      case 0:
         var10002 = "PUBLIC";
         break;
      case 1:
         var10002 = "SOCIAL";
         break;
      case 2:
         var10002 = "SOCIAL_1P";
         break;
      default:
         throw new IllegalArgumentException((new StringBuilder(43)).append("Unknown leaderboard collection: ").append(var1).toString());
      }

      return var10000.zzg("Collection", var10002).zzg("RawPlayerScore", var0.hasPlayerInfo() ? var0.getRawPlayerScore() : "none").zzg("DisplayPlayerScore", var0.hasPlayerInfo() ? var0.getDisplayPlayerScore() : "none").zzg("PlayerRank", var0.hasPlayerInfo() ? var0.getPlayerRank() : "none").zzg("DisplayPlayerRank", var0.hasPlayerInfo() ? var0.getDisplayPlayerRank() : "none").zzg("NumScores", var0.getNumScores()).zzg("TopPageNextToken", var0.zzvo()).zzg("WindowPageNextToken", var0.zzvq()).zzg("WindowPagePrevToken", var0.zzvp()).toString();
   }
}
