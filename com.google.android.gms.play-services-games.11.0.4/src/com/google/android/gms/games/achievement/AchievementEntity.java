package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzc;
import java.util.Arrays;

public final class AchievementEntity extends zzc implements Achievement {
   public static final Creator CREATOR = new zza();
   private final String zzaZb;
   private final int zzamr;
   private final String mName;
   private final String zzafa;
   private final Uri zzaZc;
   private final String zzaZd;
   private final Uri zzaZe;
   private final String zzaZf;
   private final int zzaZg;
   private final String zzaZh;
   private final PlayerEntity zzaZi;
   private final int mState;
   private final int zzaZj;
   private final String zzaZk;
   private final long zzaZl;
   private final long zzaZm;

   public AchievementEntity(Achievement var1) {
      this.zzaZb = var1.getAchievementId();
      this.zzamr = var1.getType();
      this.mName = var1.getName();
      this.zzafa = var1.getDescription();
      this.zzaZc = var1.getUnlockedImageUri();
      this.zzaZd = var1.getUnlockedImageUrl();
      this.zzaZe = var1.getRevealedImageUri();
      this.zzaZf = var1.getRevealedImageUrl();
      this.zzaZi = (PlayerEntity)var1.getPlayer().freeze();
      this.mState = var1.getState();
      this.zzaZl = var1.getLastUpdatedTimestamp();
      this.zzaZm = var1.getXpValue();
      if (var1.getType() == 1) {
         this.zzaZg = var1.getTotalSteps();
         this.zzaZh = var1.getFormattedTotalSteps();
         this.zzaZj = var1.getCurrentSteps();
         this.zzaZk = var1.getFormattedCurrentSteps();
      } else {
         this.zzaZg = 0;
         this.zzaZh = null;
         this.zzaZj = 0;
         this.zzaZk = null;
      }

      com.google.android.gms.common.internal.zzc.zzr(this.zzaZb);
      com.google.android.gms.common.internal.zzc.zzr(this.zzafa);
   }

   AchievementEntity(String var1, int var2, String var3, String var4, Uri var5, String var6, Uri var7, String var8, int var9, String var10, PlayerEntity var11, int var12, int var13, String var14, long var15, long var17) {
      this.zzaZb = var1;
      this.zzamr = var2;
      this.mName = var3;
      this.zzafa = var4;
      this.zzaZc = var5;
      this.zzaZd = var6;
      this.zzaZe = var7;
      this.zzaZf = var8;
      this.zzaZg = var9;
      this.zzaZh = var10;
      this.zzaZi = var11;
      this.mState = var12;
      this.zzaZj = var13;
      this.zzaZk = var14;
      this.zzaZl = var15;
      this.zzaZm = var17;
   }

   public final String getAchievementId() {
      return this.zzaZb;
   }

   public final int getType() {
      return this.zzamr;
   }

   public final String getName() {
      return this.mName;
   }

   public final void getName(CharArrayBuffer var1) {
      zzh.zzb(this.mName, var1);
   }

   public final String getDescription() {
      return this.zzafa;
   }

   public final void getDescription(CharArrayBuffer var1) {
      zzh.zzb(this.zzafa, var1);
   }

   public final Uri getUnlockedImageUri() {
      return this.zzaZc;
   }

   public final String getUnlockedImageUrl() {
      return this.zzaZd;
   }

   public final Uri getRevealedImageUri() {
      return this.zzaZe;
   }

   public final String getRevealedImageUrl() {
      return this.zzaZf;
   }

   public final int getTotalSteps() {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      return this.zzaZg;
   }

   public final String getFormattedTotalSteps() {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      return this.zzaZh;
   }

   public final void getFormattedTotalSteps(CharArrayBuffer var1) {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      zzh.zzb(this.zzaZh, var1);
   }

   public final Player getPlayer() {
      return this.zzaZi;
   }

   public final int getState() {
      return this.mState;
   }

   public final int getCurrentSteps() {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      return this.zzaZj;
   }

   public final String getFormattedCurrentSteps() {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      return this.zzaZk;
   }

   public final void getFormattedCurrentSteps(CharArrayBuffer var1) {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      zzh.zzb(this.zzaZk, var1);
   }

   public final long getLastUpdatedTimestamp() {
      return this.zzaZl;
   }

   public final long getXpValue() {
      return this.zzaZm;
   }

   public final Achievement freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      int var2 = 0;
      int var3 = 0;
      if (this.getType() == 1) {
         var2 = this.getCurrentSteps();
         var3 = this.getTotalSteps();
      }

      return Arrays.hashCode(new Object[]{this.getAchievementId(), this.getName(), this.getType(), this.getDescription(), this.getXpValue(), this.getState(), this.getLastUpdatedTimestamp(), this.getPlayer(), var2, var3});
   }

   public final boolean equals(Object var1) {
      if (var1 instanceof Achievement) {
         if (this == var1) {
            return true;
         }

         Achievement var4 = (Achievement)var1;
         boolean var5 = true;
         boolean var6 = true;
         if (this.getType() == 1) {
            var5 = zzbe.equal(var4.getCurrentSteps(), this.getCurrentSteps());
            var6 = zzbe.equal(var4.getTotalSteps(), this.getTotalSteps());
         }

         if (zzbe.equal(var4.getAchievementId(), this.getAchievementId()) && zzbe.equal(var4.getName(), this.getName()) && zzbe.equal(var4.getType(), this.getType()) && zzbe.equal(var4.getDescription(), this.getDescription()) && zzbe.equal(var4.getXpValue(), this.getXpValue()) && zzbe.equal(var4.getState(), this.getState()) && zzbe.equal(var4.getLastUpdatedTimestamp(), this.getLastUpdatedTimestamp()) && zzbe.equal(var4.getPlayer(), this.getPlayer()) && var5 && var6) {
            return true;
         }
      }

      return false;
   }

   public final String toString() {
      return zza(this);
   }

   static String zza(Achievement var0) {
      zzbg var1 = zzbe.zzt(var0).zzg("Id", var0.getAchievementId()).zzg("Type", var0.getType()).zzg("Name", var0.getName()).zzg("Description", var0.getDescription()).zzg("Player", var0.getPlayer()).zzg("State", var0.getState());
      if (var0.getType() == 1) {
         var1.zzg("CurrentSteps", var0.getCurrentSteps());
         var1.zzg("TotalSteps", var0.getTotalSteps());
      }

      return var1.toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getAchievementId(), false);
      zzd.zzc(var1, 2, this.getType());
      zzd.zza(var1, 3, this.getName(), false);
      zzd.zza(var1, 4, this.getDescription(), false);
      zzd.zza(var1, 5, this.getUnlockedImageUri(), var2, false);
      zzd.zza(var1, 6, this.getUnlockedImageUrl(), false);
      zzd.zza(var1, 7, this.getRevealedImageUri(), var2, false);
      zzd.zza(var1, 8, this.getRevealedImageUrl(), false);
      zzd.zzc(var1, 9, this.zzaZg);
      zzd.zza(var1, 10, this.zzaZh, false);
      zzd.zza(var1, 11, this.getPlayer(), var2, false);
      zzd.zzc(var1, 12, this.getState());
      zzd.zzc(var1, 13, this.zzaZj);
      zzd.zza(var1, 14, this.zzaZk, false);
      zzd.zza(var1, 15, this.getLastUpdatedTimestamp());
      zzd.zza(var1, 16, this.getXpValue());
      zzd.zzI(var1, var5);
   }
}
