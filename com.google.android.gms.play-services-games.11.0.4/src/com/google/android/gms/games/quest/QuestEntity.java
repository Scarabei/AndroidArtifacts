package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class QuestEntity extends com.google.android.gms.games.internal.zzc implements Quest {
   public static final Creator CREATOR = new zzc();
   private final GameEntity zzbbN;
   private final String zzbei;
   private final long zzbej;
   private final Uri zzbek;
   private final String zzbel;
   private final String zzafa;
   private final long zzbem;
   private final long zzaZl;
   private final Uri zzben;
   private final String zzbeo;
   private final String mName;
   private final long zzbep;
   private final long zzbeq;
   private final int mState;
   private final int zzamr;
   private final ArrayList zzber;

   public QuestEntity(Quest var1) {
      this.zzbbN = new GameEntity(var1.getGame());
      this.zzbei = var1.getQuestId();
      this.zzbej = var1.getAcceptedTimestamp();
      this.zzafa = var1.getDescription();
      this.zzbek = var1.getBannerImageUri();
      this.zzbel = var1.getBannerImageUrl();
      this.zzbem = var1.getEndTimestamp();
      this.zzben = var1.getIconImageUri();
      this.zzbeo = var1.getIconImageUrl();
      this.zzaZl = var1.getLastUpdatedTimestamp();
      this.mName = var1.getName();
      this.zzbep = var1.zzvu();
      this.zzbeq = var1.getStartTimestamp();
      this.mState = var1.getState();
      this.zzamr = var1.getType();
      List var2;
      int var3 = (var2 = var1.zzvt()).size();
      this.zzber = new ArrayList(var3);

      for(int var4 = 0; var4 < var3; ++var4) {
         this.zzber.add((MilestoneEntity)((Milestone)var2.get(var4)).freeze());
      }

   }

   QuestEntity(GameEntity var1, String var2, long var3, Uri var5, String var6, String var7, long var8, long var10, Uri var12, String var13, String var14, long var15, long var17, int var19, int var20, ArrayList var21) {
      this.zzbbN = var1;
      this.zzbei = var2;
      this.zzbej = var3;
      this.zzbek = var5;
      this.zzbel = var6;
      this.zzafa = var7;
      this.zzbem = var8;
      this.zzaZl = var10;
      this.zzben = var12;
      this.zzbeo = var13;
      this.mName = var14;
      this.zzbep = var15;
      this.zzbeq = var17;
      this.mState = var19;
      this.zzamr = var20;
      this.zzber = var21;
   }

   public final String getQuestId() {
      return this.zzbei;
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

   public final Uri getIconImageUri() {
      return this.zzben;
   }

   public final String getIconImageUrl() {
      return this.zzbeo;
   }

   public final Uri getBannerImageUri() {
      return this.zzbek;
   }

   public final String getBannerImageUrl() {
      return this.zzbel;
   }

   public final Milestone getCurrentMilestone() {
      return (Milestone)this.zzvt().get(0);
   }

   public final List zzvt() {
      return new ArrayList(this.zzber);
   }

   public final Game getGame() {
      return this.zzbbN;
   }

   public final int getState() {
      return this.mState;
   }

   public final int getType() {
      return this.zzamr;
   }

   public final long getAcceptedTimestamp() {
      return this.zzbej;
   }

   public final long getEndTimestamp() {
      return this.zzbem;
   }

   public final long getLastUpdatedTimestamp() {
      return this.zzaZl;
   }

   public final long zzvu() {
      return this.zzbep;
   }

   public final long getStartTimestamp() {
      return this.zzbeq;
   }

   public final boolean isEndingSoon() {
      return this.zzbep <= System.currentTimeMillis() + 1800000L;
   }

   public final Quest freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(Quest var0) {
      return Arrays.hashCode(new Object[]{var0.getGame(), var0.getQuestId(), var0.getAcceptedTimestamp(), var0.getBannerImageUri(), var0.getDescription(), var0.getEndTimestamp(), var0.getIconImageUri(), var0.getLastUpdatedTimestamp(), var0.zzvt(), var0.getName(), var0.zzvu(), var0.getStartTimestamp(), var0.getState()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(Quest var0, Object var1) {
      if (!(var1 instanceof Quest)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         Quest var2;
         return zzbe.equal((var2 = (Quest)var1).getGame(), var0.getGame()) && zzbe.equal(var2.getQuestId(), var0.getQuestId()) && zzbe.equal(var2.getAcceptedTimestamp(), var0.getAcceptedTimestamp()) && zzbe.equal(var2.getBannerImageUri(), var0.getBannerImageUri()) && zzbe.equal(var2.getDescription(), var0.getDescription()) && zzbe.equal(var2.getEndTimestamp(), var0.getEndTimestamp()) && zzbe.equal(var2.getIconImageUri(), var0.getIconImageUri()) && zzbe.equal(var2.getLastUpdatedTimestamp(), var0.getLastUpdatedTimestamp()) && zzbe.equal(var2.zzvt(), var0.zzvt()) && zzbe.equal(var2.getName(), var0.getName()) && zzbe.equal(var2.zzvu(), var0.zzvu()) && zzbe.equal(var2.getStartTimestamp(), var0.getStartTimestamp()) && zzbe.equal(var2.getState(), var0.getState());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(Quest var0) {
      return zzbe.zzt(var0).zzg("Game", var0.getGame()).zzg("QuestId", var0.getQuestId()).zzg("AcceptedTimestamp", var0.getAcceptedTimestamp()).zzg("BannerImageUri", var0.getBannerImageUri()).zzg("BannerImageUrl", var0.getBannerImageUrl()).zzg("Description", var0.getDescription()).zzg("EndTimestamp", var0.getEndTimestamp()).zzg("IconImageUri", var0.getIconImageUri()).zzg("IconImageUrl", var0.getIconImageUrl()).zzg("LastUpdatedTimestamp", var0.getLastUpdatedTimestamp()).zzg("Milestones", var0.zzvt()).zzg("Name", var0.getName()).zzg("NotifyTimestamp", var0.zzvu()).zzg("StartTimestamp", var0.getStartTimestamp()).zzg("State", var0.getState()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getGame(), var2, false);
      zzd.zza(var1, 2, this.getQuestId(), false);
      zzd.zza(var1, 3, this.getAcceptedTimestamp());
      zzd.zza(var1, 4, this.getBannerImageUri(), var2, false);
      zzd.zza(var1, 5, this.getBannerImageUrl(), false);
      zzd.zza(var1, 6, this.getDescription(), false);
      zzd.zza(var1, 7, this.getEndTimestamp());
      zzd.zza(var1, 8, this.getLastUpdatedTimestamp());
      zzd.zza(var1, 9, this.getIconImageUri(), var2, false);
      zzd.zza(var1, 10, this.getIconImageUrl(), false);
      zzd.zza(var1, 12, this.getName(), false);
      zzd.zza(var1, 13, this.zzbep);
      zzd.zza(var1, 14, this.getStartTimestamp());
      zzd.zzc(var1, 15, this.getState());
      zzd.zzc(var1, 16, this.zzamr);
      zzd.zzc(var1, 17, this.zzvt(), false);
      zzd.zzI(var1, var5);
   }
}
