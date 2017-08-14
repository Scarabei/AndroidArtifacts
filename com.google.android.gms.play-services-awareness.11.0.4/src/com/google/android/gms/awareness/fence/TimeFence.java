package com.google.android.gms.awareness.fence;

import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.internal.zzbiy;
import com.google.android.gms.internal.zzbjq;
import com.google.android.gms.internal.zzbjr;
import com.google.android.gms.internal.zzbjs;
import java.util.TimeZone;

public final class TimeFence {
   public static final int TIME_INSTANT_SUNRISE = 1;
   public static final int TIME_INSTANT_SUNSET = 2;
   public static final int TIME_INTERVAL_WEEKDAY = 1;
   public static final int TIME_INTERVAL_WEEKEND = 2;
   public static final int TIME_INTERVAL_HOLIDAY = 3;
   public static final int TIME_INTERVAL_MORNING = 4;
   public static final int TIME_INTERVAL_AFTERNOON = 5;
   public static final int TIME_INTERVAL_EVENING = 6;
   public static final int TIME_INTERVAL_NIGHT = 7;
   public static final int DAY_OF_WEEK_SUNDAY = 1;
   public static final int DAY_OF_WEEK_MONDAY = 2;
   public static final int DAY_OF_WEEK_TUESDAY = 3;
   public static final int DAY_OF_WEEK_WEDNESDAY = 4;
   public static final int DAY_OF_WEEK_THURSDAY = 5;
   public static final int DAY_OF_WEEK_FRIDAY = 6;
   public static final int DAY_OF_WEEK_SATURDAY = 7;

   public static AwarenessFence inInterval(long var0, long var2) {
      return zzbiy.zza(zzbjr.zze(var0, var2));
   }

   public static AwarenessFence inDailyInterval(@Nullable TimeZone var0, long var1, long var3) {
      return zzbiy.zza(zzbjr.zza(2, var0, var1, var3));
   }

   /** @deprecated */
   @Deprecated
   public static AwarenessFence inSundayInterval(@Nullable TimeZone var0, long var1, long var3) {
      return zzbiy.zza(zzbjr.zza(5, var0, var1, var3));
   }

   /** @deprecated */
   @Deprecated
   public static AwarenessFence inMondayInterval(@Nullable TimeZone var0, long var1, long var3) {
      return zzbiy.zza(zzbjr.zza(6, var0, var1, var3));
   }

   /** @deprecated */
   @Deprecated
   public static AwarenessFence inTuesdayInterval(@Nullable TimeZone var0, long var1, long var3) {
      return zzbiy.zza(zzbjr.zza(7, var0, var1, var3));
   }

   /** @deprecated */
   @Deprecated
   public static AwarenessFence inWednesdayInterval(@Nullable TimeZone var0, long var1, long var3) {
      return zzbiy.zza(zzbjr.zza(8, var0, var1, var3));
   }

   /** @deprecated */
   @Deprecated
   public static AwarenessFence inThursdayInterval(@Nullable TimeZone var0, long var1, long var3) {
      return zzbiy.zza(zzbjr.zza(9, var0, var1, var3));
   }

   /** @deprecated */
   @Deprecated
   public static AwarenessFence inFridayInterval(@Nullable TimeZone var0, long var1, long var3) {
      return zzbiy.zza(zzbjr.zza(10, var0, var1, var3));
   }

   /** @deprecated */
   @Deprecated
   public static AwarenessFence inSaturdayInterval(@Nullable TimeZone var0, long var1, long var3) {
      return zzbiy.zza(zzbjr.zza(11, var0, var1, var3));
   }

   public static AwarenessFence inIntervalOfDay(int var0, @Nullable TimeZone var1, long var2, long var4) {
      return zzbiy.zza(zzbjr.zzb(var0, var1, var2, var4));
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence inTimeInterval(int var0) {
      return zzbiy.zza(zzbjs.zzj(1, var0));
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public static AwarenessFence aroundTimeInstant(int var0, long var1, long var3) {
      switch(var0) {
      case 1:
         return zzbiy.zza(zzbjq.zzc(var1, var3));
      case 2:
         return zzbiy.zza(zzbjq.zzd(var1, var3));
      default:
         throw new IllegalArgumentException((new StringBuilder(40)).append("Unknown time instant label = ").append(var0).toString());
      }
   }
}
