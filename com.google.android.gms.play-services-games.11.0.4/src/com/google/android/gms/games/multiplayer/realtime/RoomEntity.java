package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import java.util.Arrays;

public final class RoomEntity extends GamesDowngradeableSafeParcel implements Room {
   public static final Creator CREATOR = new RoomEntity.zza();
   private final String zzaZN;
   private final String zzbdN;
   private final long mCreationTimestamp;
   private final int zzbdO;
   private final String zzafa;
   private final int zzbdu;
   private final Bundle zzbdL;
   private final ArrayList zzbdt;
   private final int zzbdP;

   public RoomEntity(Room var1) {
      this.zzaZN = var1.getRoomId();
      this.zzbdN = var1.getCreatorId();
      this.mCreationTimestamp = var1.getCreationTimestamp();
      this.zzbdO = var1.getStatus();
      this.zzafa = var1.getDescription();
      this.zzbdu = var1.getVariant();
      this.zzbdL = var1.getAutoMatchCriteria();
      ArrayList var2;
      int var3 = (var2 = var1.getParticipants()).size();
      this.zzbdt = new ArrayList(var3);

      for(int var4 = 0; var4 < var3; ++var4) {
         this.zzbdt.add((ParticipantEntity)((Participant)var2.get(var4)).freeze());
      }

      this.zzbdP = var1.getAutoMatchWaitEstimateSeconds();
   }

   RoomEntity(String var1, String var2, long var3, int var5, String var6, int var7, Bundle var8, ArrayList var9, int var10) {
      this.zzaZN = var1;
      this.zzbdN = var2;
      this.mCreationTimestamp = var3;
      this.zzbdO = var5;
      this.zzafa = var6;
      this.zzbdu = var7;
      this.zzbdL = var8;
      this.zzbdt = var9;
      this.zzbdP = var10;
   }

   public final String getRoomId() {
      return this.zzaZN;
   }

   public final String getCreatorId() {
      return this.zzbdN;
   }

   public final long getCreationTimestamp() {
      return this.mCreationTimestamp;
   }

   public final int getStatus() {
      return this.zzbdO;
   }

   public final String getDescription() {
      return this.zzafa;
   }

   public final void getDescription(CharArrayBuffer var1) {
      zzh.zzb(this.zzafa, var1);
   }

   public final int getVariant() {
      return this.zzbdu;
   }

   public final Bundle getAutoMatchCriteria() {
      return this.zzbdL;
   }

   public final ArrayList getParticipants() {
      return new ArrayList(this.zzbdt);
   }

   public final int getAutoMatchWaitEstimateSeconds() {
      return this.zzbdP;
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

   public final Room freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(Room var0) {
      return Arrays.hashCode(new Object[]{var0.getRoomId(), var0.getCreatorId(), var0.getCreationTimestamp(), var0.getStatus(), var0.getDescription(), var0.getVariant(), var0.getAutoMatchCriteria(), var0.getParticipants(), var0.getAutoMatchWaitEstimateSeconds()});
   }

   public final boolean equals(Object var1) {
      return zza(this, (Object)var1);
   }

   static boolean zza(Room var0, Object var1) {
      if (!(var1 instanceof Room)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         Room var2;
         return zzbe.equal((var2 = (Room)var1).getRoomId(), var0.getRoomId()) && zzbe.equal(var2.getCreatorId(), var0.getCreatorId()) && zzbe.equal(var2.getCreationTimestamp(), var0.getCreationTimestamp()) && zzbe.equal(var2.getStatus(), var0.getStatus()) && zzbe.equal(var2.getDescription(), var0.getDescription()) && zzbe.equal(var2.getVariant(), var0.getVariant()) && zzbe.equal(var2.getAutoMatchCriteria(), var0.getAutoMatchCriteria()) && zzbe.equal(var2.getParticipants(), var0.getParticipants()) && zzbe.equal(var2.getAutoMatchWaitEstimateSeconds(), var0.getAutoMatchWaitEstimateSeconds());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(Room var0) {
      return zzbe.zzt(var0).zzg("RoomId", var0.getRoomId()).zzg("CreatorId", var0.getCreatorId()).zzg("CreationTimestamp", var0.getCreationTimestamp()).zzg("RoomStatus", var0.getStatus()).zzg("Description", var0.getDescription()).zzg("Variant", var0.getVariant()).zzg("AutoMatchCriteria", var0.getAutoMatchCriteria()).zzg("Participants", var0.getParticipants()).zzg("AutoMatchWaitEstimateSeconds", var0.getAutoMatchWaitEstimateSeconds()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getRoomId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getCreatorId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getCreationTimestamp());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getStatus());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getDescription(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getVariant());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getAutoMatchCriteria(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 8, this.getParticipants(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 9, this.getAutoMatchWaitEstimateSeconds());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static int zza(Room var0, String var1) {
      ArrayList var2 = var0.getParticipants();
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         Participant var5;
         if ((var5 = (Participant)var2.get(var3)).getParticipantId().equals(var1)) {
            return var5.getStatus();
         }
      }

      String var6 = String.valueOf(var0.getRoomId());
      throw new IllegalStateException((new StringBuilder(28 + String.valueOf(var1).length() + String.valueOf(var6).length())).append("Participant ").append(var1).append(" is not in room ").append(var6).toString());
   }

   static ArrayList zzc(Room var0) {
      ArrayList var1;
      int var2 = (var1 = var0.getParticipants()).size();
      ArrayList var3 = new ArrayList(var2);

      for(int var4 = 0; var4 < var2; ++var4) {
         Participant var5 = (Participant)var1.get(var4);
         var3.add(var5.getParticipantId());
      }

      return var3;
   }

   static String zzb(Room var0, String var1) {
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

   static Participant zzc(Room var0, String var1) {
      ArrayList var2 = var0.getParticipants();
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         Participant var5;
         if ((var5 = (Participant)var2.get(var3)).getParticipantId().equals(var1)) {
            return var5;
         }
      }

      String var6 = String.valueOf(var0.getRoomId());
      throw new IllegalStateException((new StringBuilder(29 + String.valueOf(var1).length() + String.valueOf(var6).length())).append("Participant ").append(var1).append(" is not in match ").append(var6).toString());
   }

   static final class zza extends zze {
      public final RoomEntity zzj(Parcel var1) {
         if (!RoomEntity.zze(RoomEntity.zzrx()) && !RoomEntity.zzcA(RoomEntity.class.getCanonicalName())) {
            String var2 = var1.readString();
            String var3 = var1.readString();
            long var4 = var1.readLong();
            int var6 = var1.readInt();
            String var7 = var1.readString();
            int var8 = var1.readInt();
            Bundle var9 = var1.readBundle();
            int var10 = var1.readInt();
            ArrayList var11 = new ArrayList(var10);

            for(int var12 = 0; var12 < var10; ++var12) {
               var11.add((ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(var1));
            }

            return new RoomEntity(var2, var3, var4, var6, var7, var8, var9, var11, -1);
         } else {
            return super.zzj(var1);
         }
      }

      // $FF: synthetic method
      public final Object createFromParcel(Parcel var1) {
         return this.zzj(var1);
      }
   }
}
