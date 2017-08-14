package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;

public final class PlayerRef extends com.google.android.gms.common.data.zzc implements Player {
   private final com.google.android.gms.games.internal.player.zze zzaYZ;
   private final PlayerLevelInfo zzaYI;
   private final com.google.android.gms.games.internal.player.zzd zzaZa;

   public PlayerRef(DataHolder var1, int var2) {
      this(var1, var2, (String)null);
   }

   public PlayerRef(DataHolder var1, int var2, String var3) {
      super(var1, var2);
      this.zzaYZ = new com.google.android.gms.games.internal.player.zze(var3);
      this.zzaZa = new com.google.android.gms.games.internal.player.zzd(var1, var2, this.zzaYZ);
      if (!this.zzcx(this.zzaYZ.zzbcj) && this.getLong(this.zzaYZ.zzbcj) != -1L) {
         int var4 = this.getInteger(this.zzaYZ.zzbck);
         int var5 = this.getInteger(this.zzaYZ.zzbcn);
         PlayerLevel var6;
         PlayerLevel var7 = var6 = new PlayerLevel(var4, this.getLong(this.zzaYZ.zzbcl), this.getLong(this.zzaYZ.zzbcm));
         if (var4 != var5) {
            var7 = new PlayerLevel(var5, this.getLong(this.zzaYZ.zzbcm), this.getLong(this.zzaYZ.zzbco));
         }

         this.zzaYI = new PlayerLevelInfo(this.getLong(this.zzaYZ.zzbcj), this.getLong(this.zzaYZ.zzbcp), var6, var7);
      } else {
         this.zzaYI = null;
      }
   }

   public final String getPlayerId() {
      return this.getString(this.zzaYZ.zzbca);
   }

   public final String getDisplayName() {
      return this.getString(this.zzaYZ.zzbcb);
   }

   public final void getDisplayName(CharArrayBuffer var1) {
      this.zza(this.zzaYZ.zzbcb, var1);
   }

   public final String zzuj() {
      return this.getString(this.zzaYZ.zzbcz);
   }

   public final String getName() {
      return this.getString(this.zzaYZ.name);
   }

   public final boolean zzuk() {
      return this.getBoolean(this.zzaYZ.zzbcy);
   }

   public final boolean hasIconImage() {
      return this.getIconImageUri() != null;
   }

   public final Uri getIconImageUri() {
      return this.zzcw(this.zzaYZ.zzbcc);
   }

   public final String getIconImageUrl() {
      return this.getString(this.zzaYZ.zzbcd);
   }

   public final boolean hasHiResImage() {
      return this.getHiResImageUri() != null;
   }

   public final Uri getHiResImageUri() {
      return this.zzcw(this.zzaYZ.zzbce);
   }

   public final String getHiResImageUrl() {
      return this.getString(this.zzaYZ.zzbcf);
   }

   public final long getRetrievedTimestamp() {
      return this.getLong(this.zzaYZ.zzbcg);
   }

   public final long getLastPlayedWithTimestamp() {
      return this.zzcv(this.zzaYZ.zzbci) && !this.zzcx(this.zzaYZ.zzbci) ? this.getLong(this.zzaYZ.zzbci) : -1L;
   }

   public final int zzul() {
      return this.getInteger(this.zzaYZ.zzbch);
   }

   public final boolean zzum() {
      return this.getBoolean(this.zzaYZ.zzbcr);
   }

   public final String getTitle() {
      return this.getString(this.zzaYZ.title);
   }

   public final void getTitle(CharArrayBuffer var1) {
      this.zza(this.zzaYZ.title, var1);
   }

   public final PlayerLevelInfo getLevelInfo() {
      return this.zzaYI;
   }

   public final com.google.android.gms.games.internal.player.zza zzun() {
      return this.zzcx(this.zzaYZ.zzbcs) ? null : this.zzaZa;
   }

   public final Uri getBannerImageLandscapeUri() {
      return this.zzcw(this.zzaYZ.zzbcA);
   }

   public final String getBannerImageLandscapeUrl() {
      return this.getString(this.zzaYZ.zzbcB);
   }

   public final Uri getBannerImagePortraitUri() {
      return this.zzcw(this.zzaYZ.zzbcC);
   }

   public final String getBannerImagePortraitUrl() {
      return this.getString(this.zzaYZ.zzbcD);
   }

   public final int zzuo() {
      return this.getInteger(this.zzaYZ.zzbcE);
   }

   public final long zzup() {
      return this.getLong(this.zzaYZ.zzbcF);
   }

   public final boolean isMuted() {
      return this.getBoolean(this.zzaYZ.zzbcG);
   }

   public final int hashCode() {
      return PlayerEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return PlayerEntity.zza(this, var1);
   }

   public final String toString() {
      return PlayerEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((PlayerEntity)((Player)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new PlayerEntity(this);
   }
}
