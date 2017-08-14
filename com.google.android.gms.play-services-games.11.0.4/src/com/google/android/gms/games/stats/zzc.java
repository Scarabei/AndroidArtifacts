package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;

public final class zzc extends com.google.android.gms.common.data.zzc implements PlayerStats {
   private Bundle zzbeW;

   zzc(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final float getAverageSessionLength() {
      return this.getFloat("ave_session_length_minutes");
   }

   public final float getChurnProbability() {
      return this.getFloat("churn_probability");
   }

   public final int getDaysSinceLastPlayed() {
      return this.getInteger("days_since_last_played");
   }

   public final int getNumberOfPurchases() {
      return this.getInteger("num_purchases");
   }

   public final int getNumberOfSessions() {
      return this.getInteger("num_sessions");
   }

   public final float getSessionPercentile() {
      return this.getFloat("num_sessions_percentile");
   }

   public final float getSpendPercentile() {
      return this.getFloat("spend_percentile");
   }

   public final float getSpendProbability() {
      return !this.zzcv("spend_probability") ? -1.0F : this.getFloat("spend_probability");
   }

   public final float getHighSpenderProbability() {
      return !this.zzcv("high_spender_probability") ? -1.0F : this.getFloat("high_spender_probability");
   }

   public final float getTotalSpendNext28Days() {
      return !this.zzcv("total_spend_next_28_days") ? -1.0F : this.getFloat("total_spend_next_28_days");
   }

   public final Bundle zzvw() {
      if (this.zzbeW != null) {
         return this.zzbeW;
      } else {
         this.zzbeW = new Bundle();
         String var1 = this.getString("unknown_raw_keys");
         String var2 = this.getString("unknown_raw_values");
         if (var1 != null && var2 != null) {
            String[] var3 = var1.split(",");
            String[] var4 = var2.split(",");
            boolean var10000 = var3.length <= var4.length;
            String var7 = "Invalid raw arguments!";
            if (!var10000) {
               throw new IllegalStateException(String.valueOf(var7));
            }

            for(int var5 = 0; var5 < var3.length; ++var5) {
               this.zzbeW.putString(var3[var5], var4[var5]);
            }
         }

         return this.zzbeW;
      }
   }

   public final int hashCode() {
      return zza.zza(this);
   }

   public final boolean equals(Object var1) {
      return zza.zza(this, var1);
   }

   public final String toString() {
      return zza.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((zza)((PlayerStats)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new zza(this);
   }
}
