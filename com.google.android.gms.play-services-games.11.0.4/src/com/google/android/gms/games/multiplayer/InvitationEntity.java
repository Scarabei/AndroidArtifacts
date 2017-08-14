package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.ArrayList;
import java.util.Arrays;

public final class InvitationEntity extends GamesDowngradeableSafeParcel implements Invitation {
   public static final Creator CREATOR = new InvitationEntity.zza();
   private final GameEntity zzbbN;
   private final String zzajX;
   private final long mCreationTimestamp;
   private final int zzbdr;
   private final ParticipantEntity zzbds;
   private final ArrayList zzbdt;
   private final int zzbdu;
   private final int zzbdv;

   InvitationEntity(Invitation var1) {
      this.zzbbN = new GameEntity(var1.getGame());
      this.zzajX = var1.getInvitationId();
      this.mCreationTimestamp = var1.getCreationTimestamp();
      this.zzbdr = var1.getInvitationType();
      this.zzbdu = var1.getVariant();
      this.zzbdv = var1.getAvailableAutoMatchSlots();
      String var2 = var1.getInviter().getParticipantId();
      Participant var3 = null;
      ArrayList var4;
      int var5 = (var4 = var1.getParticipants()).size();
      this.zzbdt = new ArrayList(var5);

      for(int var6 = 0; var6 < var5; ++var6) {
         Participant var7;
         if ((var7 = (Participant)var4.get(var6)).getParticipantId().equals(var2)) {
            var3 = var7;
         }

         this.zzbdt.add((ParticipantEntity)var7.freeze());
      }

      zzbo.zzb(var3, "Must have a valid inviter!");
      this.zzbds = (ParticipantEntity)var3.freeze();
   }

   InvitationEntity(GameEntity var1, String var2, long var3, int var5, ParticipantEntity var6, ArrayList var7, int var8, int var9) {
      this.zzbbN = var1;
      this.zzajX = var2;
      this.mCreationTimestamp = var3;
      this.zzbdr = var5;
      this.zzbds = var6;
      this.zzbdt = var7;
      this.zzbdu = var8;
      this.zzbdv = var9;
   }

   public final Game getGame() {
      return this.zzbbN;
   }

   public final String getInvitationId() {
      return this.zzajX;
   }

   public final Participant getInviter() {
      return this.zzbds;
   }

   public final long getCreationTimestamp() {
      return this.mCreationTimestamp;
   }

   public final ArrayList getParticipants() {
      return new ArrayList(this.zzbdt);
   }

   public final int getInvitationType() {
      return this.zzbdr;
   }

   public final int getVariant() {
      return this.zzbdu;
   }

   public final int getAvailableAutoMatchSlots() {
      return this.zzbdv;
   }

   public final Invitation freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(Invitation var0) {
      return Arrays.hashCode(new Object[]{var0.getGame(), var0.getInvitationId(), var0.getCreationTimestamp(), var0.getInvitationType(), var0.getInviter(), var0.getParticipants(), var0.getVariant(), var0.getAvailableAutoMatchSlots()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(Invitation var0, Object var1) {
      if (!(var1 instanceof Invitation)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         Invitation var2;
         return zzbe.equal((var2 = (Invitation)var1).getGame(), var0.getGame()) && zzbe.equal(var2.getInvitationId(), var0.getInvitationId()) && zzbe.equal(var2.getCreationTimestamp(), var0.getCreationTimestamp()) && zzbe.equal(var2.getInvitationType(), var0.getInvitationType()) && zzbe.equal(var2.getInviter(), var0.getInviter()) && zzbe.equal(var2.getParticipants(), var0.getParticipants()) && zzbe.equal(var2.getVariant(), var0.getVariant()) && zzbe.equal(var2.getAvailableAutoMatchSlots(), var0.getAvailableAutoMatchSlots());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(Invitation var0) {
      return zzbe.zzt(var0).zzg("Game", var0.getGame()).zzg("InvitationId", var0.getInvitationId()).zzg("CreationTimestamp", var0.getCreationTimestamp()).zzg("InvitationType", var0.getInvitationType()).zzg("Inviter", var0.getInviter()).zzg("Participants", var0.getParticipants()).zzg("Variant", var0.getVariant()).zzg("AvailableAutoMatchSlots", var0.getAvailableAutoMatchSlots()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getGame(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getInvitationId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getCreationTimestamp());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getInvitationType());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getInviter(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getParticipants(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.getVariant());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 8, this.getAvailableAutoMatchSlots());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static final class zza extends zza {
      public final InvitationEntity zzh(Parcel var1) {
         if (!InvitationEntity.zze(InvitationEntity.zzrx()) && !InvitationEntity.zzcA(InvitationEntity.class.getCanonicalName())) {
            GameEntity var2 = (GameEntity)GameEntity.CREATOR.createFromParcel(var1);
            String var3 = var1.readString();
            long var4 = var1.readLong();
            int var6 = var1.readInt();
            ParticipantEntity var7 = (ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(var1);
            int var8 = var1.readInt();
            ArrayList var9 = new ArrayList(var8);

            for(int var10 = 0; var10 < var8; ++var10) {
               var9.add((ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(var1));
            }

            return new InvitationEntity(var2, var3, var4, var6, var7, var9, -1, 0);
         } else {
            return super.zzh(var1);
         }
      }

      // $FF: synthetic method
      public final Object createFromParcel(Parcel var1) {
         return this.zzh(var1);
      }
   }
}
