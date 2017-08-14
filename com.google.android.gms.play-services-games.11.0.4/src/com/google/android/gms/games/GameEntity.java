package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.Arrays;

public final class GameEntity extends GamesDowngradeableSafeParcel implements Game {
   public static final Creator CREATOR = new GameEntity.zza();
   private final String zzaoM;
   private final String zzalP;
   private final String zzaXR;
   private final String zzaXS;
   private final String zzafa;
   private final String zzaXT;
   private final Uri zzaXU;
   private final Uri zzaXV;
   private final Uri zzaXW;
   private final boolean zzaXX;
   private final boolean zzaXY;
   private final String zzaXZ;
   private final int zzaYa;
   private final int zzaYb;
   private final int zzaYc;
   private final boolean zzaYd;
   private final boolean zzaYe;
   private final String zzaYf;
   private final String zzaYg;
   private final String zzaYh;
   private final boolean zzQN;
   private final boolean zzaYi;
   private final boolean zzaYj;
   private final String zzaYk;
   private final boolean zzaYl;

   public GameEntity(Game var1) {
      this.zzaoM = var1.getApplicationId();
      this.zzaXR = var1.getPrimaryCategory();
      this.zzaXS = var1.getSecondaryCategory();
      this.zzafa = var1.getDescription();
      this.zzaXT = var1.getDeveloperName();
      this.zzalP = var1.getDisplayName();
      this.zzaXU = var1.getIconImageUri();
      this.zzaYf = var1.getIconImageUrl();
      this.zzaXV = var1.getHiResImageUri();
      this.zzaYg = var1.getHiResImageUrl();
      this.zzaXW = var1.getFeaturedImageUri();
      this.zzaYh = var1.getFeaturedImageUrl();
      this.zzaXX = var1.zzud();
      this.zzaXY = var1.zzuf();
      this.zzaXZ = var1.zzug();
      this.zzaYa = 1;
      this.zzaYb = var1.getAchievementTotalCount();
      this.zzaYc = var1.getLeaderboardCount();
      this.zzaYd = var1.isRealTimeMultiplayerEnabled();
      this.zzaYe = var1.isTurnBasedMultiplayerEnabled();
      this.zzQN = var1.isMuted();
      this.zzaYi = var1.zzue();
      this.zzaYj = var1.areSnapshotsEnabled();
      this.zzaYk = var1.getThemeColor();
      this.zzaYl = var1.hasGamepadSupport();
   }

   GameEntity(String var1, String var2, String var3, String var4, String var5, String var6, Uri var7, Uri var8, Uri var9, boolean var10, boolean var11, String var12, int var13, int var14, int var15, boolean var16, boolean var17, String var18, String var19, String var20, boolean var21, boolean var22, boolean var23, String var24, boolean var25) {
      this.zzaoM = var1;
      this.zzalP = var2;
      this.zzaXR = var3;
      this.zzaXS = var4;
      this.zzafa = var5;
      this.zzaXT = var6;
      this.zzaXU = var7;
      this.zzaYf = var18;
      this.zzaXV = var8;
      this.zzaYg = var19;
      this.zzaXW = var9;
      this.zzaYh = var20;
      this.zzaXX = var10;
      this.zzaXY = var11;
      this.zzaXZ = var12;
      this.zzaYa = var13;
      this.zzaYb = var14;
      this.zzaYc = var15;
      this.zzaYd = var16;
      this.zzaYe = var17;
      this.zzQN = var21;
      this.zzaYi = var22;
      this.zzaYj = var23;
      this.zzaYk = var24;
      this.zzaYl = var25;
   }

   public final String getApplicationId() {
      return this.zzaoM;
   }

   public final String getDisplayName() {
      return this.zzalP;
   }

   public final void getDisplayName(CharArrayBuffer var1) {
      com.google.android.gms.common.util.zzh.zzb(this.zzalP, var1);
   }

   public final String getPrimaryCategory() {
      return this.zzaXR;
   }

   public final String getSecondaryCategory() {
      return this.zzaXS;
   }

   public final String getDescription() {
      return this.zzafa;
   }

   public final void getDescription(CharArrayBuffer var1) {
      com.google.android.gms.common.util.zzh.zzb(this.zzafa, var1);
   }

   public final String getDeveloperName() {
      return this.zzaXT;
   }

   public final void getDeveloperName(CharArrayBuffer var1) {
      com.google.android.gms.common.util.zzh.zzb(this.zzaXT, var1);
   }

   public final Uri getIconImageUri() {
      return this.zzaXU;
   }

   public final String getIconImageUrl() {
      return this.zzaYf;
   }

   public final Uri getHiResImageUri() {
      return this.zzaXV;
   }

   public final String getHiResImageUrl() {
      return this.zzaYg;
   }

   public final Uri getFeaturedImageUri() {
      return this.zzaXW;
   }

   public final String getFeaturedImageUrl() {
      return this.zzaYh;
   }

   public final boolean isMuted() {
      return this.zzQN;
   }

   public final boolean zzue() {
      return this.zzaYi;
   }

   public final boolean zzud() {
      return this.zzaXX;
   }

   public final boolean zzuf() {
      return this.zzaXY;
   }

   public final String zzug() {
      return this.zzaXZ;
   }

   public final int getAchievementTotalCount() {
      return this.zzaYb;
   }

   public final int getLeaderboardCount() {
      return this.zzaYc;
   }

   public final boolean isRealTimeMultiplayerEnabled() {
      return this.zzaYd;
   }

   public final boolean isTurnBasedMultiplayerEnabled() {
      return this.zzaYe;
   }

   public final boolean areSnapshotsEnabled() {
      return this.zzaYj;
   }

   public final String getThemeColor() {
      return this.zzaYk;
   }

   public final boolean hasGamepadSupport() {
      return this.zzaYl;
   }

   public final Game freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(Game var0) {
      return Arrays.hashCode(new Object[]{var0.getApplicationId(), var0.getDisplayName(), var0.getPrimaryCategory(), var0.getSecondaryCategory(), var0.getDescription(), var0.getDeveloperName(), var0.getIconImageUri(), var0.getHiResImageUri(), var0.getFeaturedImageUri(), var0.zzud(), var0.zzuf(), var0.zzug(), var0.getAchievementTotalCount(), var0.getLeaderboardCount(), var0.isRealTimeMultiplayerEnabled(), var0.isTurnBasedMultiplayerEnabled(), var0.isMuted(), var0.zzue(), var0.areSnapshotsEnabled(), var0.getThemeColor(), var0.hasGamepadSupport()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(Game var0, Object var1) {
      if (!(var1 instanceof Game)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         Game var2;
         return zzbe.equal((var2 = (Game)var1).getApplicationId(), var0.getApplicationId()) && zzbe.equal(var2.getDisplayName(), var0.getDisplayName()) && zzbe.equal(var2.getPrimaryCategory(), var0.getPrimaryCategory()) && zzbe.equal(var2.getSecondaryCategory(), var0.getSecondaryCategory()) && zzbe.equal(var2.getDescription(), var0.getDescription()) && zzbe.equal(var2.getDeveloperName(), var0.getDeveloperName()) && zzbe.equal(var2.getIconImageUri(), var0.getIconImageUri()) && zzbe.equal(var2.getHiResImageUri(), var0.getHiResImageUri()) && zzbe.equal(var2.getFeaturedImageUri(), var0.getFeaturedImageUri()) && zzbe.equal(var2.zzud(), var0.zzud()) && zzbe.equal(var2.zzuf(), var0.zzuf()) && zzbe.equal(var2.zzug(), var0.zzug()) && zzbe.equal(var2.getAchievementTotalCount(), var0.getAchievementTotalCount()) && zzbe.equal(var2.getLeaderboardCount(), var0.getLeaderboardCount()) && zzbe.equal(var2.isRealTimeMultiplayerEnabled(), var0.isRealTimeMultiplayerEnabled()) && zzbe.equal(var2.isTurnBasedMultiplayerEnabled(), var0.isTurnBasedMultiplayerEnabled() && zzbe.equal(var2.isMuted(), var0.isMuted()) && zzbe.equal(var2.zzue(), var0.zzue())) && zzbe.equal(var2.areSnapshotsEnabled(), var0.areSnapshotsEnabled()) && zzbe.equal(var2.getThemeColor(), var0.getThemeColor()) && zzbe.equal(var2.hasGamepadSupport(), var0.hasGamepadSupport());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(Game var0) {
      return zzbe.zzt(var0).zzg("ApplicationId", var0.getApplicationId()).zzg("DisplayName", var0.getDisplayName()).zzg("PrimaryCategory", var0.getPrimaryCategory()).zzg("SecondaryCategory", var0.getSecondaryCategory()).zzg("Description", var0.getDescription()).zzg("DeveloperName", var0.getDeveloperName()).zzg("IconImageUri", var0.getIconImageUri()).zzg("IconImageUrl", var0.getIconImageUrl()).zzg("HiResImageUri", var0.getHiResImageUri()).zzg("HiResImageUrl", var0.getHiResImageUrl()).zzg("FeaturedImageUri", var0.getFeaturedImageUri()).zzg("FeaturedImageUrl", var0.getFeaturedImageUrl()).zzg("PlayEnabledGame", var0.zzud()).zzg("InstanceInstalled", var0.zzuf()).zzg("InstancePackageName", var0.zzug()).zzg("AchievementTotalCount", var0.getAchievementTotalCount()).zzg("LeaderboardCount", var0.getLeaderboardCount()).zzg("RealTimeMultiplayerEnabled", var0.isRealTimeMultiplayerEnabled()).zzg("TurnBasedMultiplayerEnabled", var0.isTurnBasedMultiplayerEnabled()).zzg("AreSnapshotsEnabled", var0.areSnapshotsEnabled()).zzg("ThemeColor", var0.getThemeColor()).zzg("HasGamepadSupport", var0.hasGamepadSupport()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getApplicationId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getDisplayName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getPrimaryCategory(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getSecondaryCategory(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getDescription(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getDeveloperName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getIconImageUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getHiResImageUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.getFeaturedImageUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzaXX);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzaXY);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzaXZ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 13, this.zzaYa);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 14, this.getAchievementTotalCount());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 15, this.getLeaderboardCount());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 16, this.isRealTimeMultiplayerEnabled());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 17, this.isTurnBasedMultiplayerEnabled());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 18, this.getIconImageUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 19, this.getHiResImageUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 20, this.getFeaturedImageUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 21, this.zzQN);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 22, this.zzaYi);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 23, this.areSnapshotsEnabled());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 24, this.getThemeColor(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 25, this.hasGamepadSupport());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static final class zza extends zza {
      public final GameEntity zzf(Parcel var1) {
         if (!GameEntity.zze(GameEntity.zzrx()) && !GameEntity.zzcA(GameEntity.class.getCanonicalName())) {
            String var2 = var1.readString();
            String var3 = var1.readString();
            String var4 = var1.readString();
            String var5 = var1.readString();
            String var6 = var1.readString();
            String var7 = var1.readString();
            String var8;
            Uri var9 = (var8 = var1.readString()) == null ? null : Uri.parse(var8);
            String var10;
            Uri var11 = (var10 = var1.readString()) == null ? null : Uri.parse(var10);
            String var12;
            Uri var13 = (var12 = var1.readString()) == null ? null : Uri.parse(var12);
            boolean var14 = var1.readInt() > 0;
            boolean var15 = var1.readInt() > 0;
            String var16 = var1.readString();
            int var17 = var1.readInt();
            int var18 = var1.readInt();
            int var19 = var1.readInt();
            return new GameEntity(var2, var3, var4, var5, var6, var7, var9, var11, var13, var14, var15, var16, var17, var18, var19, false, false, (String)null, (String)null, (String)null, false, false, false, (String)null, false);
         } else {
            return super.zzf(var1);
         }
      }

      // $FF: synthetic method
      public final Object createFromParcel(Parcel var1) {
         return this.zzf(var1);
      }
   }
}
