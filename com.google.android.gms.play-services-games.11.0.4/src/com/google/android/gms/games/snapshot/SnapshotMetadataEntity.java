package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import java.util.Arrays;

public final class SnapshotMetadataEntity extends com.google.android.gms.games.internal.zzc implements SnapshotMetadata {
   public static final Creator CREATOR = new zzf();
   private final GameEntity zzbbN;
   private final PlayerEntity zzbeF;
   private final String zzaZJ;
   private final Uri zzbeC;
   private final String zzbeG;
   private final String zzaoy;
   private final String zzafa;
   private final long zzbeH;
   private final long zzbeI;
   private final float zzbeJ;
   private final String zzbeK;
   private final boolean zzbeL;
   private final long zzbeM;
   private final String zzbeN;

   public SnapshotMetadataEntity(SnapshotMetadata var1) {
      this.zzbbN = new GameEntity(var1.getGame());
      this.zzbeF = new PlayerEntity(var1.getOwner());
      this.zzaZJ = var1.getSnapshotId();
      this.zzbeC = var1.getCoverImageUri();
      this.zzbeG = var1.getCoverImageUrl();
      this.zzbeJ = var1.getCoverImageAspectRatio();
      this.zzaoy = var1.getTitle();
      this.zzafa = var1.getDescription();
      this.zzbeH = var1.getLastModifiedTimestamp();
      this.zzbeI = var1.getPlayedTime();
      this.zzbeK = var1.getUniqueName();
      this.zzbeL = var1.hasChangePending();
      this.zzbeM = var1.getProgressValue();
      this.zzbeN = var1.getDeviceName();
   }

   SnapshotMetadataEntity(GameEntity var1, PlayerEntity var2, String var3, Uri var4, String var5, String var6, String var7, long var8, long var10, float var12, String var13, boolean var14, long var15, String var17) {
      this.zzbbN = var1;
      this.zzbeF = var2;
      this.zzaZJ = var3;
      this.zzbeC = var4;
      this.zzbeG = var5;
      this.zzbeJ = var12;
      this.zzaoy = var6;
      this.zzafa = var7;
      this.zzbeH = var8;
      this.zzbeI = var10;
      this.zzbeK = var13;
      this.zzbeL = var14;
      this.zzbeM = var15;
      this.zzbeN = var17;
   }

   public final Game getGame() {
      return this.zzbbN;
   }

   public final Player getOwner() {
      return this.zzbeF;
   }

   public final String getSnapshotId() {
      return this.zzaZJ;
   }

   public final Uri getCoverImageUri() {
      return this.zzbeC;
   }

   public final String getCoverImageUrl() {
      return this.zzbeG;
   }

   public final float getCoverImageAspectRatio() {
      return this.zzbeJ;
   }

   public final String getUniqueName() {
      return this.zzbeK;
   }

   public final String getTitle() {
      return this.zzaoy;
   }

   public final String getDescription() {
      return this.zzafa;
   }

   public final void getDescription(CharArrayBuffer var1) {
      zzh.zzb(this.zzafa, var1);
   }

   public final long getLastModifiedTimestamp() {
      return this.zzbeH;
   }

   public final long getPlayedTime() {
      return this.zzbeI;
   }

   public final boolean hasChangePending() {
      return this.zzbeL;
   }

   public final long getProgressValue() {
      return this.zzbeM;
   }

   public final String getDeviceName() {
      return this.zzbeN;
   }

   public final SnapshotMetadata freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(SnapshotMetadata var0) {
      return Arrays.hashCode(new Object[]{var0.getGame(), var0.getOwner(), var0.getSnapshotId(), var0.getCoverImageUri(), var0.getCoverImageAspectRatio(), var0.getTitle(), var0.getDescription(), var0.getLastModifiedTimestamp(), var0.getPlayedTime(), var0.getUniqueName(), var0.hasChangePending(), var0.getProgressValue(), var0.getDeviceName()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(SnapshotMetadata var0, Object var1) {
      if (!(var1 instanceof SnapshotMetadata)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         SnapshotMetadata var2;
         return zzbe.equal((var2 = (SnapshotMetadata)var1).getGame(), var0.getGame()) && zzbe.equal(var2.getOwner(), var0.getOwner()) && zzbe.equal(var2.getSnapshotId(), var0.getSnapshotId()) && zzbe.equal(var2.getCoverImageUri(), var0.getCoverImageUri()) && zzbe.equal(var2.getCoverImageAspectRatio(), var0.getCoverImageAspectRatio()) && zzbe.equal(var2.getTitle(), var0.getTitle()) && zzbe.equal(var2.getDescription(), var0.getDescription()) && zzbe.equal(var2.getLastModifiedTimestamp(), var0.getLastModifiedTimestamp()) && zzbe.equal(var2.getPlayedTime(), var0.getPlayedTime()) && zzbe.equal(var2.getUniqueName(), var0.getUniqueName()) && zzbe.equal(var2.hasChangePending(), var0.hasChangePending()) && zzbe.equal(var2.getProgressValue(), var0.getProgressValue()) && zzbe.equal(var2.getDeviceName(), var0.getDeviceName());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(SnapshotMetadata var0) {
      return zzbe.zzt(var0).zzg("Game", var0.getGame()).zzg("Owner", var0.getOwner()).zzg("SnapshotId", var0.getSnapshotId()).zzg("CoverImageUri", var0.getCoverImageUri()).zzg("CoverImageUrl", var0.getCoverImageUrl()).zzg("CoverImageAspectRatio", var0.getCoverImageAspectRatio()).zzg("Description", var0.getDescription()).zzg("LastModifiedTimestamp", var0.getLastModifiedTimestamp()).zzg("PlayedTime", var0.getPlayedTime()).zzg("UniqueName", var0.getUniqueName()).zzg("ChangePending", var0.hasChangePending()).zzg("ProgressValue", var0.getProgressValue()).zzg("DeviceName", var0.getDeviceName()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getGame(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getOwner(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getSnapshotId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getCoverImageUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getCoverImageUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaoy, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getDescription(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.getLastModifiedTimestamp());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.getPlayedTime());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.getCoverImageAspectRatio());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.getUniqueName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.hasChangePending());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.getProgressValue());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.getDeviceName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
