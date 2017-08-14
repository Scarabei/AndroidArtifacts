package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import java.util.Arrays;

public final class TurnBasedMatchEntity extends com.google.android.gms.games.internal.zzc implements TurnBasedMatch {
   public static final Creator CREATOR = new zzc();
   private final GameEntity zzbbN;
   private final String zzaZZ;
   private final String zzbdN;
   private final long mCreationTimestamp;
   private final String zzbdV;
   private final long zzaZl;
   private final String zzbdW;
   private final int zzbdX;
   private final int zzbdu;
   private final int mVersion;
   private final byte[] zzbdY;
   private final ArrayList zzbdt;
   private final String zzbdZ;
   private final byte[] zzbea;
   private final int zzbeb;
   private final Bundle zzbdL;
   private final int zzbec;
   private final boolean zzbed;
   private final String zzafa;
   private final String zzbee;

   public TurnBasedMatchEntity(TurnBasedMatch var1) {
      this.zzbbN = new GameEntity(var1.getGame());
      this.zzaZZ = var1.getMatchId();
      this.zzbdN = var1.getCreatorId();
      this.mCreationTimestamp = var1.getCreationTimestamp();
      this.zzbdV = var1.getLastUpdaterId();
      this.zzaZl = var1.getLastUpdatedTimestamp();
      this.zzbdW = var1.getPendingParticipantId();
      this.zzbdX = var1.getStatus();
      this.zzbec = var1.getTurnStatus();
      this.zzbdu = var1.getVariant();
      this.mVersion = var1.getVersion();
      this.zzbdZ = var1.getRematchId();
      this.zzbeb = var1.getMatchNumber();
      this.zzbdL = var1.getAutoMatchCriteria();
      this.zzbed = var1.isLocallyModified();
      this.zzafa = var1.getDescription();
      this.zzbee = var1.getDescriptionParticipantId();
      byte[] var2;
      if ((var2 = var1.getData()) == null) {
         this.zzbdY = null;
      } else {
         this.zzbdY = new byte[var2.length];
         System.arraycopy(var2, 0, this.zzbdY, 0, var2.length);
      }

      byte[] var3;
      if ((var3 = var1.getPreviousMatchData()) == null) {
         this.zzbea = null;
      } else {
         this.zzbea = new byte[var3.length];
         System.arraycopy(var3, 0, this.zzbea, 0, var3.length);
      }

      ArrayList var4;
      int var5 = (var4 = var1.getParticipants()).size();
      this.zzbdt = new ArrayList(var5);

      for(int var6 = 0; var6 < var5; ++var6) {
         this.zzbdt.add((ParticipantEntity)((Participant)var4.get(var6)).freeze());
      }

   }

   TurnBasedMatchEntity(GameEntity var1, String var2, String var3, long var4, String var6, long var7, String var9, int var10, int var11, int var12, byte[] var13, ArrayList var14, String var15, byte[] var16, int var17, Bundle var18, int var19, boolean var20, String var21, String var22) {
      this.zzbbN = var1;
      this.zzaZZ = var2;
      this.zzbdN = var3;
      this.mCreationTimestamp = var4;
      this.zzbdV = var6;
      this.zzaZl = var7;
      this.zzbdW = var9;
      this.zzbdX = var10;
      this.zzbec = var19;
      this.zzbdu = var11;
      this.mVersion = var12;
      this.zzbdY = var13;
      this.zzbdt = var14;
      this.zzbdZ = var15;
      this.zzbea = var16;
      this.zzbeb = var17;
      this.zzbdL = var18;
      this.zzbed = var20;
      this.zzafa = var21;
      this.zzbee = var22;
   }

   public final Game getGame() {
      return this.zzbbN;
   }

   public final String getMatchId() {
      return this.zzaZZ;
   }

   public final String getCreatorId() {
      return this.zzbdN;
   }

   public final long getCreationTimestamp() {
      return this.mCreationTimestamp;
   }

   public final String getLastUpdaterId() {
      return this.zzbdV;
   }

   public final long getLastUpdatedTimestamp() {
      return this.zzaZl;
   }

   public final String getPendingParticipantId() {
      return this.zzbdW;
   }

   public final int getStatus() {
      return this.zzbdX;
   }

   public final int getTurnStatus() {
      return this.zzbec;
   }

   public final String getDescription() {
      return this.zzafa;
   }

   public final String getDescriptionParticipantId() {
      return this.zzbee;
   }

   public final Participant getDescriptionParticipant() {
      String var1;
      return (var1 = this.getDescriptionParticipantId()) == null ? null : this.getParticipant(var1);
   }

   public final void getDescription(CharArrayBuffer var1) {
      zzh.zzb(this.zzafa, var1);
   }

   public final int getVariant() {
      return this.zzbdu;
   }

   public final byte[] getData() {
      return this.zzbdY;
   }

   public final int getVersion() {
      return this.mVersion;
   }

   public final String getRematchId() {
      return this.zzbdZ;
   }

   public final byte[] getPreviousMatchData() {
      return this.zzbea;
   }

   public final int getMatchNumber() {
      return this.zzbeb;
   }

   public final Bundle getAutoMatchCriteria() {
      return this.zzbdL;
   }

   public final int getAvailableAutoMatchSlots() {
      return this.zzbdL == null ? 0 : this.zzbdL.getInt("max_automatch_players");
   }

   public final boolean canRematch() {
      return this.zzbdX == 2 && this.zzbdZ == null;
   }

   public final boolean isLocallyModified() {
      return this.zzbed;
   }

   public final int getParticipantStatus(String var1) {
      return zza(this, (String)var1);
   }

   public final ArrayList getParticipantIds() {
      return zzc(this);
   }

   public final String getParticipantId(String var1) {
      return zzb(this, var1);
   }

   public final Participant getParticipant(String var1) {
      return zzc(this, var1);
   }

   public final ArrayList getParticipants() {
      return new ArrayList(this.zzbdt);
   }

   public final TurnBasedMatch freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(TurnBasedMatch var0) {
      return Arrays.hashCode(new Object[]{var0.getGame(), var0.getMatchId(), var0.getCreatorId(), var0.getCreationTimestamp(), var0.getLastUpdaterId(), var0.getLastUpdatedTimestamp(), var0.getPendingParticipantId(), var0.getStatus(), var0.getTurnStatus(), var0.getDescription(), var0.getVariant(), var0.getVersion(), var0.getParticipants(), var0.getRematchId(), var0.getMatchNumber(), var0.getAutoMatchCriteria(), var0.getAvailableAutoMatchSlots(), var0.isLocallyModified()});
   }

   public final boolean equals(Object var1) {
      return zza(this, (Object)var1);
   }

   static boolean zza(TurnBasedMatch var0, Object var1) {
      if (!(var1 instanceof TurnBasedMatch)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         TurnBasedMatch var2;
         return zzbe.equal((var2 = (TurnBasedMatch)var1).getGame(), var0.getGame()) && zzbe.equal(var2.getMatchId(), var0.getMatchId()) && zzbe.equal(var2.getCreatorId(), var0.getCreatorId()) && zzbe.equal(var2.getCreationTimestamp(), var0.getCreationTimestamp()) && zzbe.equal(var2.getLastUpdaterId(), var0.getLastUpdaterId()) && zzbe.equal(var2.getLastUpdatedTimestamp(), var0.getLastUpdatedTimestamp()) && zzbe.equal(var2.getPendingParticipantId(), var0.getPendingParticipantId()) && zzbe.equal(var2.getStatus(), var0.getStatus()) && zzbe.equal(var2.getTurnStatus(), var0.getTurnStatus()) && zzbe.equal(var2.getDescription(), var0.getDescription()) && zzbe.equal(var2.getVariant(), var0.getVariant()) && zzbe.equal(var2.getVersion(), var0.getVersion()) && zzbe.equal(var2.getParticipants(), var0.getParticipants()) && zzbe.equal(var2.getRematchId(), var0.getRematchId()) && zzbe.equal(var2.getMatchNumber(), var0.getMatchNumber()) && zzbe.equal(var2.getAutoMatchCriteria(), var0.getAutoMatchCriteria()) && zzbe.equal(var2.getAvailableAutoMatchSlots(), var0.getAvailableAutoMatchSlots()) && zzbe.equal(var2.isLocallyModified(), var0.isLocallyModified());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(TurnBasedMatch var0) {
      return zzbe.zzt(var0).zzg("Game", var0.getGame()).zzg("MatchId", var0.getMatchId()).zzg("CreatorId", var0.getCreatorId()).zzg("CreationTimestamp", var0.getCreationTimestamp()).zzg("LastUpdaterId", var0.getLastUpdaterId()).zzg("LastUpdatedTimestamp", var0.getLastUpdatedTimestamp()).zzg("PendingParticipantId", var0.getPendingParticipantId()).zzg("MatchStatus", var0.getStatus()).zzg("TurnStatus", var0.getTurnStatus()).zzg("Description", var0.getDescription()).zzg("Variant", var0.getVariant()).zzg("Data", var0.getData()).zzg("Version", var0.getVersion()).zzg("Participants", var0.getParticipants()).zzg("RematchId", var0.getRematchId()).zzg("PreviousData", var0.getPreviousMatchData()).zzg("MatchNumber", var0.getMatchNumber()).zzg("AutoMatchCriteria", var0.getAutoMatchCriteria()).zzg("AvailableAutoMatchSlots", var0.getAvailableAutoMatchSlots()).zzg("LocallyModified", var0.isLocallyModified()).zzg("DescriptionParticipantId", var0.getDescriptionParticipantId()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getGame(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getMatchId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getCreatorId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getCreationTimestamp());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getLastUpdaterId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getLastUpdatedTimestamp());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getPendingParticipantId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 8, this.getStatus());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.getVariant());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.getVersion());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.getData(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 13, this.getParticipants(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.getRematchId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.getPreviousMatchData(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 16, this.getMatchNumber());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 17, this.getAutoMatchCriteria(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 18, this.getTurnStatus());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 19, this.isLocallyModified());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 20, this.getDescription(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 21, this.getDescriptionParticipantId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static int zza(TurnBasedMatch var0, String var1) {
      ArrayList var2 = var0.getParticipants();
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         Participant var5;
         if ((var5 = (Participant)var2.get(var3)).getParticipantId().equals(var1)) {
            return var5.getStatus();
         }
      }

      String var6 = String.valueOf(var0.getMatchId());
      throw new IllegalStateException((new StringBuilder(29 + String.valueOf(var1).length() + String.valueOf(var6).length())).append("Participant ").append(var1).append(" is not in match ").append(var6).toString());
   }

   static ArrayList zzc(TurnBasedMatch var0) {
      ArrayList var1;
      int var2 = (var1 = var0.getParticipants()).size();
      ArrayList var3 = new ArrayList(var2);

      for(int var4 = 0; var4 < var2; ++var4) {
         Participant var5 = (Participant)var1.get(var4);
         var3.add(var5.getParticipantId());
      }

      return var3;
   }

   static String zzb(TurnBasedMatch var0, String var1) {
      String var2 = null;
      ArrayList var3 = var0.getParticipants();
      int var4 = 0;

      for(int var5 = var3.size(); var4 < var5; ++var4) {
         Participant var6;
         Player var7;
         if ((var7 = (var6 = (Participant)var3.get(var4)).getPlayer()) != null && var7.getPlayerId().equals(var1)) {
            var2 = var6.getParticipantId();
            break;
         }
      }

      return var2;
   }

   static Participant zzc(TurnBasedMatch var0, String var1) {
      ArrayList var2 = var0.getParticipants();
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         Participant var5;
         if ((var5 = (Participant)var2.get(var3)).getParticipantId().equals(var1)) {
            return var5;
         }
      }

      String var6 = String.valueOf(var0.getMatchId());
      throw new IllegalStateException((new StringBuilder(29 + String.valueOf(var1).length() + String.valueOf(var6).length())).append("Participant ").append(var1).append(" is not in match ").append(var6).toString());
   }
}
