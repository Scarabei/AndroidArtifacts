package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public final class PlayerLevelInfo extends com.google.android.gms.games.internal.zzc {
   public static final Creator CREATOR = new zzi();
   private final long zzaYV;
   private final long zzaYW;
   private final PlayerLevel zzaYX;
   private final PlayerLevel zzaYY;

   public PlayerLevelInfo(long var1, long var3, PlayerLevel var5, PlayerLevel var6) {
      zzbo.zzae(var1 != -1L);
      zzbo.zzu(var5);
      zzbo.zzu(var6);
      this.zzaYV = var1;
      this.zzaYW = var3;
      this.zzaYX = var5;
      this.zzaYY = var6;
   }

   public final long getCurrentXpTotal() {
      return this.zzaYV;
   }

   public final long getLastLevelUpTimestamp() {
      return this.zzaYW;
   }

   public final PlayerLevel getCurrentLevel() {
      return this.zzaYX;
   }

   public final PlayerLevel getNextLevel() {
      return this.zzaYY;
   }

   public final boolean isMaxLevel() {
      return this.zzaYX.equals(this.zzaYY);
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof PlayerLevelInfo)) {
         return false;
      } else if (var1 == this) {
         return true;
      } else {
         PlayerLevelInfo var2 = (PlayerLevelInfo)var1;
         return zzbe.equal(this.zzaYV, var2.zzaYV) && zzbe.equal(this.zzaYW, var2.zzaYW) && zzbe.equal(this.zzaYX, var2.zzaYX) && zzbe.equal(this.zzaYY, var2.zzaYY);
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaYV, this.zzaYW, this.zzaYX, this.zzaYY});
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getCurrentXpTotal());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getLastLevelUpTimestamp());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getCurrentLevel(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getNextLevel(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
