package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzauh extends zza implements Weather {
   public static final Creator CREATOR = new zzaui();
   private final float zzaot;
   private final float zzaou;
   private final float zzaov;
   private final int zzaow;
   private final int[] zzaox;

   public zzauh(float var1, float var2, float var3, int var4, int[] var5) {
      this.zzaot = var1;
      this.zzaou = var2;
      this.zzaov = var3;
      this.zzaow = var4;
      this.zzaox = var5;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaot);
      zzd.zza(var1, 3, this.zzaou);
      zzd.zza(var1, 4, this.zzaov);
      zzd.zzc(var1, 5, this.getHumidity());
      zzd.zza(var1, 6, this.getConditions(), false);
      zzd.zzI(var1, var5);
   }

   public final float getDewPoint(int var1) {
      return zza(var1, this.zzaov);
   }

   public final float getFeelsLikeTemperature(int var1) {
      return zza(var1, this.zzaou);
   }

   public final float getTemperature(int var1) {
      return zza(var1, this.zzaot);
   }

   public final int getHumidity() {
      return this.zzaow;
   }

   public final int[] getConditions() {
      return this.zzaox;
   }

   public final String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder()).append("Temp=").append(this.getTemperature(1)).append("F/").append(this.getTemperature(2)).append("C, Feels=").append(this.getFeelsLikeTemperature(1)).append("F/").append(this.getFeelsLikeTemperature(2)).append("C, Dew=").append(this.getDewPoint(1)).append("F/").append(this.getDewPoint(2)).append("C, Humidity=").append(this.getHumidity()).append(", Condition=");
      if (this.getConditions() == null) {
         var1.append("unknown");
      } else {
         var1.append("[");
         boolean var2 = true;
         int[] var3;
         int var4 = (var3 = this.getConditions()).length;

         for(int var5 = 0; var5 < var4; ++var5) {
            int var6 = var3[var5];
            if (!var2) {
               var1.append(",");
            }

            var2 = false;
            var1.append(var6);
         }

         var1.append("]");
      }

      return var1.toString();
   }

   private static float zza(int var0, float var1) {
      switch(var0) {
      case 1:
         return var1;
      case 2:
         return 5.0F * (var1 - 32.0F) / 9.0F;
      default:
         zzeq.zza("WeatherImpl", "Invalid temperature unit %s", (Object)var0);
         throw new IllegalArgumentException("Invalid temperature unit");
      }
   }
}
