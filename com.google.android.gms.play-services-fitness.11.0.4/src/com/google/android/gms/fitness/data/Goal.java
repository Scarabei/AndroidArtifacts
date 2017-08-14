package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Goal extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int OBJECTIVE_TYPE_METRIC = 1;
   public static final int OBJECTIVE_TYPE_DURATION = 2;
   public static final int OBJECTIVE_TYPE_FREQUENCY = 3;
   private final int versionCode;
   private final long zzaUH;
   private final long zzaUI;
   private final List zzaUJ;
   private final Goal.Recurrence zzaUK;
   private final int zzaUL;
   private final Goal.MetricObjective zzaUM;
   private final Goal.DurationObjective zzaUN;
   private final Goal.FrequencyObjective zzaUO;
   public static final Creator CREATOR = new zzs();

   public long getCreateTime(TimeUnit var1) {
      return var1.convert(this.zzaUH, TimeUnit.NANOSECONDS);
   }

   public long getStartTime(Calendar var1, TimeUnit var2) {
      if (this.zzaUK != null) {
         Calendar var3;
         (var3 = Calendar.getInstance()).setTime(var1.getTime());
         switch(this.zzaUK.zzaUS) {
         case 1:
            var3.set(11, 0);
            return var2.convert(var3.getTimeInMillis(), TimeUnit.MILLISECONDS);
         case 2:
            var3.set(7, 2);
            var3.set(11, 0);
            return var2.convert(var3.getTimeInMillis(), TimeUnit.MILLISECONDS);
         case 3:
            var3.set(5, 1);
            var3.set(11, 0);
            return var2.convert(var3.getTimeInMillis(), TimeUnit.MILLISECONDS);
         default:
            int var4 = this.zzaUK.zzaUS;
            throw new IllegalArgumentException((new StringBuilder(24)).append("Invalid unit ").append(var4).toString());
         }
      } else {
         return var2.convert(this.zzaUH, TimeUnit.NANOSECONDS);
      }
   }

   public long getEndTime(Calendar var1, TimeUnit var2) {
      if (this.zzaUK != null) {
         Calendar var3;
         (var3 = Calendar.getInstance()).setTime(var1.getTime());
         switch(this.zzaUK.zzaUS) {
         case 1:
            var3.add(5, 1);
            var3.set(11, 0);
            return var2.convert(var3.getTimeInMillis(), TimeUnit.MILLISECONDS);
         case 2:
            var3.add(4, 1);
            var3.set(7, 2);
            var3.set(11, 0);
            return var2.convert(var3.getTimeInMillis(), TimeUnit.MILLISECONDS);
         case 3:
            var3.add(2, 1);
            var3.set(5, 1);
            var3.set(11, 0);
            return var2.convert(var3.getTimeInMillis(), TimeUnit.MILLISECONDS);
         default:
            int var4 = this.zzaUK.zzaUS;
            throw new IllegalArgumentException((new StringBuilder(24)).append("Invalid unit ").append(var4).toString());
         }
      } else {
         return var2.convert(this.zzaUI, TimeUnit.NANOSECONDS);
      }
   }

   @Nullable
   public String getActivityName() {
      return !this.zzaUJ.isEmpty() && this.zzaUJ.size() <= 1 ? com.google.android.gms.fitness.zza.getName(((Integer)this.zzaUJ.get(0)).intValue()) : null;
   }

   public Goal.Recurrence getRecurrence() {
      return this.zzaUK;
   }

   public int getObjectiveType() {
      return this.zzaUL;
   }

   private static String zzaW(int var0) {
      switch(var0) {
      case 0:
         return "unknown";
      case 1:
         return "metric";
      case 2:
         return "duration";
      case 3:
         return "frequency";
      default:
         throw new IllegalArgumentException("invalid objective type value");
      }
   }

   public Goal.MetricObjective getMetricObjective() {
      this.zzaX(1);
      return this.zzaUM;
   }

   public Goal.DurationObjective getDurationObjective() {
      this.zzaX(2);
      return this.zzaUN;
   }

   public Goal.FrequencyObjective getFrequencyObjective() {
      this.zzaX(3);
      return this.zzaUO;
   }

   Goal(int var1, long var2, long var4, List var6, Goal.Recurrence var7, int var8, Goal.MetricObjective var9, Goal.DurationObjective var10, Goal.FrequencyObjective var11) {
      this.versionCode = var1;
      this.zzaUH = var2;
      this.zzaUI = var4;
      this.zzaUJ = var6;
      this.zzaUK = var7;
      this.zzaUL = var8;
      this.zzaUM = var9;
      this.zzaUN = var10;
      this.zzaUO = var11;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof Goal) {
            Goal var3 = (Goal)var1;
            if (this.zzaUH == var3.zzaUH && this.zzaUI == var3.zzaUI && zzbe.equal(this.zzaUJ, var3.zzaUJ) && zzbe.equal(this.zzaUK, var3.zzaUK) && this.zzaUL == var3.zzaUL && zzbe.equal(this.zzaUM, var3.zzaUM) && zzbe.equal(this.zzaUN, var3.zzaUN) && zzbe.equal(this.zzaUO, var3.zzaUO)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return this.zzaUL;
   }

   public String toString() {
      return zzbe.zzt(this).zzg("activity", this.getActivityName()).zzg("recurrence", this.zzaUK).zzg("metricObjective", this.zzaUM).zzg("durationObjective", this.zzaUN).zzg("frequencyObjective", this.zzaUO).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaUH);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaUI);
      com.google.android.gms.common.internal.safeparcel.zzd.zzd(var1, 3, this.zzaUJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getRecurrence(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getObjectiveType());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaUM, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaUN, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaUO, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   private final void zzaX(int var1) throws Goal.MismatchedGoalException {
      if (var1 != this.zzaUL) {
         throw new Goal.MismatchedGoalException(String.format("%s goal does not have %s objective", zzaW(this.zzaUL), zzaW(var1)));
      }
   }

   public static class MismatchedGoalException extends IllegalStateException {
      public MismatchedGoalException(String var1) {
         super(var1);
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ObjectiveType {
   }

   public static class FrequencyObjective extends com.google.android.gms.common.internal.safeparcel.zza {
      private final int versionCode;
      private final int frequency;
      public static final Creator CREATOR = new zzr();

      public int getFrequency() {
         return this.frequency;
      }

      FrequencyObjective(int var1, int var2) {
         this.versionCode = var1;
         this.frequency = var2;
      }

      public FrequencyObjective(int var1) {
         this(1, var1);
      }

      public boolean equals(Object var1) {
         if (this != var1) {
            if (var1 instanceof Goal.FrequencyObjective) {
               Goal.FrequencyObjective var2 = (Goal.FrequencyObjective)var1;
               if (this.frequency == var2.frequency) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public int hashCode() {
         return this.frequency;
      }

      public String toString() {
         return zzbe.zzt(this).zzg("frequency", this.frequency).toString();
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getFrequency());
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class DurationObjective extends com.google.android.gms.common.internal.safeparcel.zza {
      private final int versionCode;
      private final long zzaUP;
      public static final Creator CREATOR = new zzp();

      public long getDuration(TimeUnit var1) {
         return var1.convert(this.zzaUP, TimeUnit.NANOSECONDS);
      }

      DurationObjective(int var1, long var2) {
         this.versionCode = var1;
         this.zzaUP = var2;
      }

      public DurationObjective(long var1, TimeUnit var3) {
         this(1, var3.toNanos(var1));
      }

      public boolean equals(Object var1) {
         if (this != var1) {
            if (var1 instanceof Goal.DurationObjective) {
               Goal.DurationObjective var2 = (Goal.DurationObjective)var1;
               if (this.zzaUP == var2.zzaUP) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public int hashCode() {
         return (int)this.zzaUP;
      }

      public String toString() {
         return zzbe.zzt(this).zzg("duration", this.zzaUP).toString();
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaUP);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class MetricObjective extends com.google.android.gms.common.internal.safeparcel.zza {
      private final int versionCode;
      private final String zzaUQ;
      private final double value;
      private final double zzaUR;
      public static final Creator CREATOR = new zzx();

      public String getDataTypeName() {
         return this.zzaUQ;
      }

      public double getValue() {
         return this.value;
      }

      MetricObjective(int var1, String var2, double var3, double var5) {
         this.versionCode = var1;
         this.zzaUQ = var2;
         this.value = var3;
         this.zzaUR = var5;
      }

      public MetricObjective(String var1, double var2) {
         this(1, var1, var2, 0.0D);
      }

      public boolean equals(Object var1) {
         if (this != var1) {
            if (var1 instanceof Goal.MetricObjective) {
               Goal.MetricObjective var3 = (Goal.MetricObjective)var1;
               if (zzbe.equal(this.zzaUQ, var3.zzaUQ) && this.value == var3.value && this.zzaUR == var3.zzaUR) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public int hashCode() {
         return this.zzaUQ.hashCode();
      }

      public String toString() {
         return zzbe.zzt(this).zzg("dataTypeName", this.zzaUQ).zzg("value", this.value).zzg("initialValue", this.zzaUR).toString();
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getDataTypeName(), false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getValue());
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaUR);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class Recurrence extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final int UNIT_DAY = 1;
      public static final int UNIT_WEEK = 2;
      public static final int UNIT_MONTH = 3;
      private final int versionCode;
      private final int count;
      private final int zzaUS;
      public static final Creator CREATOR = new zzab();

      public int getCount() {
         return this.count;
      }

      public int getUnit() {
         return this.zzaUS;
      }

      Recurrence(int var1, int var2, int var3) {
         this.versionCode = var1;
         this.count = var2;
         zzbo.zzae(var3 > 0 && var3 <= 3);
         this.zzaUS = var3;
      }

      public Recurrence(int var1, int var2) {
         this(1, var1, var2);
      }

      public boolean equals(Object var1) {
         if (this != var1) {
            if (var1 instanceof Goal.Recurrence) {
               Goal.Recurrence var3 = (Goal.Recurrence)var1;
               if (this.count == var3.count && this.zzaUS == var3.zzaUS) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public int hashCode() {
         return this.zzaUS;
      }

      public String toString() {
         zzbg var10000 = zzbe.zzt(this).zzg("count", this.count);
         String var10002;
         switch(this.zzaUS) {
         case 1:
            var10002 = "day";
            break;
         case 2:
            var10002 = "week";
            break;
         case 3:
            var10002 = "month";
            break;
         default:
            throw new IllegalArgumentException("invalid unit value");
         }

         return var10000.zzg("unit", var10002).toString();
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getCount());
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getUnit());
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface RecurrenceUnit {
      }
   }
}
