package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.Arrays;

public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant {
   public static final Creator CREATOR = new ParticipantEntity.zza();
   private final String zzbah;
   private final String zzalP;
   private final Uri zzaXU;
   private final Uri zzaXV;
   private final int zzLe;
   private final String zzbdx;
   private final boolean zzbdy;
   private final PlayerEntity zzaZi;
   private final int zzapi;
   private final ParticipantResult zzbdz;
   private final String zzaYf;
   private final String zzaYg;

   public ParticipantEntity(Participant var1) {
      this.zzbah = var1.getParticipantId();
      this.zzalP = var1.getDisplayName();
      this.zzaXU = var1.getIconImageUri();
      this.zzaXV = var1.getHiResImageUri();
      this.zzLe = var1.getStatus();
      this.zzbdx = var1.zzvr();
      this.zzbdy = var1.isConnectedToRoom();
      Player var2 = var1.getPlayer();
      this.zzaZi = var2 == null ? null : new PlayerEntity(var2);
      this.zzapi = var1.getCapabilities();
      this.zzbdz = var1.getResult();
      this.zzaYf = var1.getIconImageUrl();
      this.zzaYg = var1.getHiResImageUrl();
   }

   ParticipantEntity(String var1, String var2, Uri var3, Uri var4, int var5, String var6, boolean var7, PlayerEntity var8, int var9, ParticipantResult var10, String var11, String var12) {
      this.zzbah = var1;
      this.zzalP = var2;
      this.zzaXU = var3;
      this.zzaXV = var4;
      this.zzLe = var5;
      this.zzbdx = var6;
      this.zzbdy = var7;
      this.zzaZi = var8;
      this.zzapi = var9;
      this.zzbdz = var10;
      this.zzaYf = var11;
      this.zzaYg = var12;
   }

   public final int getStatus() {
      return this.zzLe;
   }

   public final String zzvr() {
      return this.zzbdx;
   }

   public final boolean isConnectedToRoom() {
      return this.zzbdy;
   }

   public final String getDisplayName() {
      return this.zzaZi == null ? this.zzalP : this.zzaZi.getDisplayName();
   }

   public final void getDisplayName(CharArrayBuffer var1) {
      if (this.zzaZi == null) {
         zzh.zzb(this.zzalP, var1);
      } else {
         this.zzaZi.getDisplayName(var1);
      }
   }

   public final Uri getIconImageUri() {
      return this.zzaZi == null ? this.zzaXU : this.zzaZi.getIconImageUri();
   }

   public final String getIconImageUrl() {
      return this.zzaZi == null ? this.zzaYf : this.zzaZi.getIconImageUrl();
   }

   public final Uri getHiResImageUri() {
      return this.zzaZi == null ? this.zzaXV : this.zzaZi.getHiResImageUri();
   }

   public final String getHiResImageUrl() {
      return this.zzaZi == null ? this.zzaYg : this.zzaZi.getHiResImageUrl();
   }

   public final String getParticipantId() {
      return this.zzbah;
   }

   public final Player getPlayer() {
      return this.zzaZi;
   }

   public final ParticipantResult getResult() {
      return this.zzbdz;
   }

   public final int getCapabilities() {
      return this.zzapi;
   }

   public final Participant freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(Participant var0) {
      return Arrays.hashCode(new Object[]{var0.getPlayer(), var0.getStatus(), var0.zzvr(), var0.isConnectedToRoom(), var0.getDisplayName(), var0.getIconImageUri(), var0.getHiResImageUri(), var0.getCapabilities(), var0.getResult(), var0.getParticipantId()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(Participant var0, Object var1) {
      if (!(var1 instanceof Participant)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         Participant var2;
         return zzbe.equal((var2 = (Participant)var1).getPlayer(), var0.getPlayer()) && zzbe.equal(var2.getStatus(), var0.getStatus()) && zzbe.equal(var2.zzvr(), var0.zzvr()) && zzbe.equal(var2.isConnectedToRoom(), var0.isConnectedToRoom()) && zzbe.equal(var2.getDisplayName(), var0.getDisplayName()) && zzbe.equal(var2.getIconImageUri(), var0.getIconImageUri()) && zzbe.equal(var2.getHiResImageUri(), var0.getHiResImageUri()) && zzbe.equal(var2.getCapabilities(), var0.getCapabilities()) && zzbe.equal(var2.getResult(), var0.getResult()) && zzbe.equal(var2.getParticipantId(), var0.getParticipantId());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(Participant var0) {
      return zzbe.zzt(var0).zzg("ParticipantId", var0.getParticipantId()).zzg("Player", var0.getPlayer()).zzg("Status", var0.getStatus()).zzg("ClientAddress", var0.zzvr()).zzg("ConnectedToRoom", var0.isConnectedToRoom()).zzg("DisplayName", var0.getDisplayName()).zzg("IconImage", var0.getIconImageUri()).zzg("IconImageUrl", var0.getIconImageUrl()).zzg("HiResImage", var0.getHiResImageUri()).zzg("HiResImageUrl", var0.getHiResImageUrl()).zzg("Capabilities", var0.getCapabilities()).zzg("Result", var0.getResult()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getParticipantId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getDisplayName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getIconImageUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getHiResImageUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getStatus());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbdx, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.isConnectedToRoom());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getPlayer(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 9, this.zzapi);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.getResult(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.getIconImageUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.getHiResImageUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static final class zza extends zzc {
      public final ParticipantEntity zzi(Parcel var1) {
         if (!ParticipantEntity.zze(ParticipantEntity.zzrx()) && !ParticipantEntity.zzcA(ParticipantEntity.class.getCanonicalName())) {
            String var2 = var1.readString();
            String var3 = var1.readString();
            String var4;
            Uri var5 = (var4 = var1.readString()) == null ? null : Uri.parse(var4);
            String var6;
            Uri var7 = (var6 = var1.readString()) == null ? null : Uri.parse(var6);
            int var8 = var1.readInt();
            String var9 = var1.readString();
            boolean var10 = var1.readInt() > 0;
            PlayerEntity var11 = var1.readInt() > 0 ? (PlayerEntity)PlayerEntity.CREATOR.createFromParcel(var1) : null;
            return new ParticipantEntity(var2, var3, var5, var7, var8, var9, var10, var11, 7, (ParticipantResult)null, (String)null, (String)null);
         } else {
            return super.zzi(var1);
         }
      }

      // $FF: synthetic method
      public final Object createFromParcel(Parcel var1) {
         return this.zzi(var1);
      }
   }
}
