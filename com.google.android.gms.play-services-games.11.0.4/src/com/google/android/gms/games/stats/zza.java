package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zza extends com.google.android.gms.games.internal.zzc implements PlayerStats {
   public static final Creator CREATOR = new zzb();
   private final float zzbeP;
   private final float zzbeQ;
   private final int zzbeR;
   private final int zzbeS;
   private final int zzbeT;
   private final float zzbeU;
   private final float zzbeV;
   private final Bundle zzbeW;
   private final float zzbeX;
   private final float zzbeY;
   private final float zzbeZ;

   public zza(PlayerStats var1) {
      this.zzbeP = var1.getAverageSessionLength();
      this.zzbeQ = var1.getChurnProbability();
      this.zzbeR = var1.getDaysSinceLastPlayed();
      this.zzbeS = var1.getNumberOfPurchases();
      this.zzbeT = var1.getNumberOfSessions();
      this.zzbeU = var1.getSessionPercentile();
      this.zzbeV = var1.getSpendPercentile();
      this.zzbeX = var1.getSpendProbability();
      this.zzbeY = var1.getHighSpenderProbability();
      this.zzbeZ = var1.getTotalSpendNext28Days();
      this.zzbeW = var1.zzvw();
   }

   zza(float var1, float var2, int var3, int var4, int var5, float var6, float var7, Bundle var8, float var9, float var10, float var11) {
      this.zzbeP = var1;
      this.zzbeQ = var2;
      this.zzbeR = var3;
      this.zzbeS = var4;
      this.zzbeT = var5;
      this.zzbeU = var6;
      this.zzbeV = var7;
      this.zzbeW = var8;
      this.zzbeX = var9;
      this.zzbeY = var10;
      this.zzbeZ = var11;
   }

   public final float getAverageSessionLength() {
      return this.zzbeP;
   }

   public final float getChurnProbability() {
      return this.zzbeQ;
   }

   public final int getDaysSinceLastPlayed() {
      return this.zzbeR;
   }

   public final int getNumberOfPurchases() {
      return this.zzbeS;
   }

   public final int getNumberOfSessions() {
      return this.zzbeT;
   }

   public final float getSessionPercentile() {
      return this.zzbeU;
   }

   public final float getSpendPercentile() {
      return this.zzbeV;
   }

   public final Bundle zzvw() {
      return this.zzbeW;
   }

   public final float getSpendProbability() {
      return this.zzbeX;
   }

   public final float getHighSpenderProbability() {
      return this.zzbeY;
   }

   public final float getTotalSpendNext28Days() {
      return this.zzbeZ;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(PlayerStats var0) {
      return Arrays.hashCode(new Object[]{var0.getAverageSessionLength(), var0.getChurnProbability(), var0.getDaysSinceLastPlayed(), var0.getNumberOfPurchases(), var0.getNumberOfSessions(), var0.getSessionPercentile(), var0.getSpendPercentile(), var0.getSpendProbability(), var0.getHighSpenderProbability(), var0.getTotalSpendNext28Days()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(PlayerStats var0, Object var1) {
      if (!(var1 instanceof PlayerStats)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         PlayerStats var2;
         return zzbe.equal((var2 = (PlayerStats)var1).getAverageSessionLength(), var0.getAverageSessionLength()) && zzbe.equal(var2.getChurnProbability(), var0.getChurnProbability()) && zzbe.equal(var2.getDaysSinceLastPlayed(), var0.getDaysSinceLastPlayed()) && zzbe.equal(var2.getNumberOfPurchases(), var0.getNumberOfPurchases()) && zzbe.equal(var2.getNumberOfSessions(), var0.getNumberOfSessions()) && zzbe.equal(var2.getSessionPercentile(), var0.getSessionPercentile()) && zzbe.equal(var2.getSpendPercentile(), var0.getSpendPercentile()) && zzbe.equal(var2.getSpendProbability(), var0.getSpendProbability()) && zzbe.equal(var2.getHighSpenderProbability(), var0.getHighSpenderProbability()) && zzbe.equal(var2.getTotalSpendNext28Days(), var0.getTotalSpendNext28Days());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(PlayerStats var0) {
      return zzbe.zzt(var0).zzg("AverageSessionLength", var0.getAverageSessionLength()).zzg("ChurnProbability", var0.getChurnProbability()).zzg("DaysSinceLastPlayed", var0.getDaysSinceLastPlayed()).zzg("NumberOfPurchases", var0.getNumberOfPurchases()).zzg("NumberOfSessions", var0.getNumberOfSessions()).zzg("SessionPercentile", var0.getSessionPercentile()).zzg("SpendPercentile", var0.getSpendPercentile()).zzg("SpendProbability", var0.getSpendProbability()).zzg("HighSpenderProbability", var0.getHighSpenderProbability()).zzg("TotalSpendNext28Days", var0.getTotalSpendNext28Days()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getAverageSessionLength());
      zzd.zza(var1, 2, this.getChurnProbability());
      zzd.zzc(var1, 3, this.getDaysSinceLastPlayed());
      zzd.zzc(var1, 4, this.getNumberOfPurchases());
      zzd.zzc(var1, 5, this.getNumberOfSessions());
      zzd.zza(var1, 6, this.getSessionPercentile());
      zzd.zza(var1, 7, this.getSpendPercentile());
      zzd.zza(var1, 8, this.zzbeW, false);
      zzd.zza(var1, 9, this.getSpendProbability());
      zzd.zza(var1, 10, this.getHighSpenderProbability());
      zzd.zza(var1, 11, this.getTotalSpendNext28Days());
      zzd.zzI(var1, var5);
   }
}
