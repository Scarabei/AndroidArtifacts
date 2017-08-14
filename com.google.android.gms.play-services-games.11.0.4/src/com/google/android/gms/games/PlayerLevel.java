package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public final class PlayerLevel extends com.google.android.gms.games.internal.zzc {
   public static final Creator CREATOR = new zzh();
   private final int zzaYS;
   private final long zzaYT;
   private final long zzaYU;

   public PlayerLevel(int var1, long var2, long var4) {
      zzbo.zza(var2 >= 0L, "Min XP must be positive!");
      zzbo.zza(var4 > var2, "Max XP must be more than min XP!");
      this.zzaYS = var1;
      this.zzaYT = var2;
      this.zzaYU = var4;
   }

   public final int getLevelNumber() {
      return this.zzaYS;
   }

   public final long getMinXp() {
      return this.zzaYT;
   }

   public final long getMaxXp() {
      return this.zzaYU;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaYS, this.zzaYT, this.zzaYU});
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof PlayerLevel)) {
         return false;
      } else if (this == var1) {
         return true;
      } else {
         PlayerLevel var2;
         return zzbe.equal((var2 = (PlayerLevel)var1).getLevelNumber(), this.getLevelNumber()) && zzbe.equal(var2.getMinXp(), this.getMinXp()) && zzbe.equal(var2.getMaxXp(), this.getMaxXp());
      }
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("LevelNumber", this.getLevelNumber()).zzg("MinXp", this.getMinXp()).zzg("MaxXp", this.getMaxXp()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getLevelNumber());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getMinXp());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getMaxXp());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
