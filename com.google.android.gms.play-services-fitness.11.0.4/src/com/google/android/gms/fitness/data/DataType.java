package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class DataType extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.data_type/";
   public static final DataType TYPE_STEP_COUNT_DELTA;
   @KeepName
   public static final DataType TYPE_STEP_COUNT_CUMULATIVE;
   public static final DataType zzaTK;
   public static final DataType TYPE_STEP_COUNT_CADENCE;
   public static final DataType zzaTL;
   public static final DataType TYPE_ACTIVITY_SEGMENT;
   public static final DataType zzaTM;
   /** @deprecated */
   @Deprecated
   public static final DataType TYPE_CALORIES_CONSUMED;
   public static final DataType TYPE_CALORIES_EXPENDED;
   public static final DataType TYPE_BASAL_METABOLIC_RATE;
   public static final DataType TYPE_POWER_SAMPLE;
   /** @deprecated */
   @Deprecated
   public static final DataType TYPE_ACTIVITY_SAMPLE;
   public static final DataType TYPE_ACTIVITY_SAMPLES;
   public static final DataType zzaTN;
   public static final DataType zzaTO;
   public static final DataType zzaTP;
   @RequiresPermission(
      value = "android.permission.BODY_SENSORS",
      conditional = true
   )
   public static final DataType TYPE_HEART_RATE_BPM;
   @RequiresPermission(
      value = "android.permission.ACCESS_FINE_LOCATION",
      conditional = true
   )
   public static final DataType TYPE_LOCATION_SAMPLE;
   @RequiresPermission(
      value = "android.permission.ACCESS_FINE_LOCATION",
      conditional = true
   )
   public static final DataType TYPE_LOCATION_TRACK;
   @RequiresPermission(
      value = "android.permission.ACCESS_FINE_LOCATION",
      conditional = true
   )
   public static final DataType TYPE_DISTANCE_DELTA;
   @RequiresPermission(
      value = "android.permission.ACCESS_FINE_LOCATION",
      conditional = true
   )
   @KeepName
   public static final DataType TYPE_DISTANCE_CUMULATIVE;
   @RequiresPermission(
      value = "android.permission.ACCESS_FINE_LOCATION",
      conditional = true
   )
   public static final DataType TYPE_SPEED;
   public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION;
   public static final DataType TYPE_CYCLING_WHEEL_RPM;
   public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE;
   public static final DataType TYPE_CYCLING_PEDALING_CADENCE;
   public static final DataType TYPE_HEIGHT;
   public static final DataType TYPE_WEIGHT;
   public static final DataType TYPE_BODY_FAT_PERCENTAGE;
   public static final DataType zzaTQ;
   public static final DataType zzaTR;
   public static final DataType TYPE_NUTRITION;
   public static final DataType TYPE_HYDRATION;
   public static final DataType TYPE_WORKOUT_EXERCISE;
   public static final DataType AGGREGATE_ACTIVITY_SUMMARY;
   public static final DataType zzaTS;
   public static final DataType AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY;
   public static final DataType AGGREGATE_STEP_COUNT_DELTA;
   public static final DataType AGGREGATE_DISTANCE_DELTA;
   /** @deprecated */
   @Deprecated
   public static final DataType AGGREGATE_CALORIES_CONSUMED;
   public static final DataType AGGREGATE_CALORIES_EXPENDED;
   @RequiresPermission(
      value = "android.permission.BODY_SENSORS",
      conditional = true
   )
   public static final DataType AGGREGATE_HEART_RATE_SUMMARY;
   @RequiresPermission(
      value = "android.permission.ACCESS_FINE_LOCATION",
      conditional = true
   )
   public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX;
   public static final DataType AGGREGATE_POWER_SUMMARY;
   public static final DataType AGGREGATE_SPEED_SUMMARY;
   public static final DataType AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY;
   public static final DataType zzaTT;
   public static final DataType zzaTU;
   public static final DataType AGGREGATE_WEIGHT_SUMMARY;
   public static final DataType AGGREGATE_HEIGHT_SUMMARY;
   public static final DataType AGGREGATE_NUTRITION_SUMMARY;
   public static final DataType AGGREGATE_HYDRATION;
   /** @deprecated */
   @Deprecated
   public static final Set AGGREGATE_INPUT_TYPES;
   private final int versionCode;
   private final String name;
   private final List zzaTV;
   public static final Creator CREATOR;

   public static List getAggregatesForInput(DataType var0) {
      List var1;
      return (var1 = (List)zza.zzaTi.get(var0)) == null ? Collections.emptyList() : Collections.unmodifiableList(var1);
   }

   public DataType(String var1, Field... var2) {
      this(1, var1, Arrays.asList(var2));
   }

   DataType(int var1, String var2, List var3) {
      this.versionCode = var1;
      this.name = var2;
      this.zzaTV = Collections.unmodifiableList(var3);
   }

   public static String getMimeType(DataType var0) {
      String var10000 = String.valueOf("vnd.google.fitness.data_type/");
      String var10001 = String.valueOf(var0.getName());
      return var10001.length() != 0 ? var10000.concat(var10001) : new String(var10000);
   }

   public final String getName() {
      return this.name;
   }

   public final List getFields() {
      return this.zzaTV;
   }

   public final int indexOf(Field var1) {
      int var2;
      if ((var2 = this.zzaTV.indexOf(var1)) < 0) {
         throw new IllegalArgumentException(String.format("%s not a field of %s", var1, this));
      } else {
         return var2;
      }
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof DataType) {
            DataType var3 = (DataType)var1;
            if (this.name.equals(var3.name) && this.zzaTV.equals(var3.zzaTV)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return this.name.hashCode();
   }

   public final String toString() {
      return String.format("DataType{%s%s}", this.name, this.zzaTV);
   }

   public final String zztO() {
      return this.name.startsWith("com.google.") ? this.name.substring(11) : this.name;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getFields(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static {
      TYPE_STEP_COUNT_DELTA = new DataType("com.google.step_count.delta", new Field[]{Field.FIELD_STEPS});
      TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", new Field[]{Field.FIELD_STEPS});
      zzaTK = new DataType("com.google.step_length", new Field[]{Field.FIELD_STEP_LENGTH});
      TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", new Field[]{Field.FIELD_RPM});
      zzaTL = new DataType("com.google.stride_model", new Field[]{Field.zzaUn});
      TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", new Field[]{Field.FIELD_ACTIVITY});
      zzaTM = new DataType("com.google.floor_change", new Field[]{Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE, Field.zzaUo, Field.zzaUr});
      TYPE_CALORIES_CONSUMED = new DataType("com.google.calories.consumed", new Field[]{Field.FIELD_CALORIES});
      TYPE_CALORIES_EXPENDED = new DataType("com.google.calories.expended", new Field[]{Field.FIELD_CALORIES});
      TYPE_BASAL_METABOLIC_RATE = new DataType("com.google.calories.bmr", new Field[]{Field.FIELD_CALORIES});
      TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", new Field[]{Field.FIELD_WATTS});
      TYPE_ACTIVITY_SAMPLE = new DataType("com.google.activity.sample", new Field[]{Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE});
      TYPE_ACTIVITY_SAMPLES = new DataType("com.google.activity.samples", new Field[]{Field.FIELD_ACTIVITY_CONFIDENCE});
      zzaTN = new DataType("com.google.accelerometer", new Field[]{Field.zza.zzaUC, Field.zza.zzaUD, Field.zza.zzaUE});
      zzaTO = new DataType("com.google.sensor.events", new Field[]{Field.zzaUu, Field.zzaUw, Field.zzaUA});
      zzaTP = new DataType("com.google.sensor.const_rate_events", new Field[]{Field.zzaUv, Field.zzaUx, Field.zzaUy, Field.zzaUz, Field.zzaUA});
      TYPE_HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", new Field[]{Field.FIELD_BPM});
      TYPE_LOCATION_SAMPLE = new DataType("com.google.location.sample", new Field[]{Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE});
      TYPE_LOCATION_TRACK = new DataType("com.google.location.track", new Field[]{Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE});
      TYPE_DISTANCE_DELTA = new DataType("com.google.distance.delta", new Field[]{Field.FIELD_DISTANCE});
      TYPE_DISTANCE_CUMULATIVE = new DataType("com.google.distance.cumulative", new Field[]{Field.FIELD_DISTANCE});
      TYPE_SPEED = new DataType("com.google.speed", new Field[]{Field.FIELD_SPEED});
      TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", new Field[]{Field.FIELD_REVOLUTIONS});
      TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", new Field[]{Field.FIELD_RPM});
      TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", new Field[]{Field.FIELD_REVOLUTIONS});
      TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", new Field[]{Field.FIELD_RPM});
      TYPE_HEIGHT = new DataType("com.google.height", new Field[]{Field.FIELD_HEIGHT});
      TYPE_WEIGHT = new DataType("com.google.weight", new Field[]{Field.FIELD_WEIGHT});
      TYPE_BODY_FAT_PERCENTAGE = new DataType("com.google.body.fat.percentage", new Field[]{Field.FIELD_PERCENTAGE});
      zzaTQ = new DataType("com.google.body.waist.circumference", new Field[]{Field.FIELD_CIRCUMFERENCE});
      zzaTR = new DataType("com.google.body.hip.circumference", new Field[]{Field.FIELD_CIRCUMFERENCE});
      TYPE_NUTRITION = new DataType("com.google.nutrition", new Field[]{Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE, Field.FIELD_FOOD_ITEM});
      TYPE_HYDRATION = new DataType("com.google.hydration", new Field[]{Field.FIELD_VOLUME});
      TYPE_WORKOUT_EXERCISE = new DataType("com.google.activity.exercise", new Field[]{Field.FIELD_EXERCISE, Field.FIELD_REPETITIONS, Field.FIELD_DURATION, Field.FIELD_RESISTANCE_TYPE, Field.FIELD_RESISTANCE});
      AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", new Field[]{Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS});
      zzaTS = new DataType("com.google.floor_change.summary", new Field[]{Field.zzaUl, Field.zzaUm, Field.zzaUp, Field.zzaUq, Field.zzaUs, Field.zzaUt});
      AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY = new DataType("com.google.calories.bmr.summary", new Field[]{Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN});
      AGGREGATE_STEP_COUNT_DELTA = TYPE_STEP_COUNT_DELTA;
      AGGREGATE_DISTANCE_DELTA = TYPE_DISTANCE_DELTA;
      AGGREGATE_CALORIES_CONSUMED = TYPE_CALORIES_CONSUMED;
      AGGREGATE_CALORIES_EXPENDED = TYPE_CALORIES_EXPENDED;
      AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", new Field[]{Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN});
      AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", new Field[]{Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE});
      AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", new Field[]{Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN});
      AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", new Field[]{Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN});
      AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY = new DataType("com.google.body.fat.percentage.summary", new Field[]{Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN});
      zzaTT = new DataType("com.google.body.hip.circumference.summary", new Field[]{Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN});
      zzaTU = new DataType("com.google.body.waist.circumference.summary", new Field[]{Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN});
      AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", new Field[]{Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN});
      AGGREGATE_HEIGHT_SUMMARY = new DataType("com.google.height.summary", new Field[]{Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN});
      AGGREGATE_NUTRITION_SUMMARY = new DataType("com.google.nutrition.summary", new Field[]{Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE});
      AGGREGATE_HYDRATION = TYPE_HYDRATION;
      (AGGREGATE_INPUT_TYPES = new com.google.android.gms.common.util.zza()).add(TYPE_ACTIVITY_SEGMENT);
      AGGREGATE_INPUT_TYPES.add(TYPE_BASAL_METABOLIC_RATE);
      AGGREGATE_INPUT_TYPES.add(TYPE_BODY_FAT_PERCENTAGE);
      AGGREGATE_INPUT_TYPES.add(zzaTR);
      AGGREGATE_INPUT_TYPES.add(zzaTQ);
      AGGREGATE_INPUT_TYPES.add(TYPE_CALORIES_CONSUMED);
      AGGREGATE_INPUT_TYPES.add(TYPE_CALORIES_EXPENDED);
      AGGREGATE_INPUT_TYPES.add(TYPE_DISTANCE_DELTA);
      AGGREGATE_INPUT_TYPES.add(zzaTM);
      AGGREGATE_INPUT_TYPES.add(TYPE_LOCATION_SAMPLE);
      AGGREGATE_INPUT_TYPES.add(TYPE_NUTRITION);
      AGGREGATE_INPUT_TYPES.add(TYPE_HYDRATION);
      AGGREGATE_INPUT_TYPES.add(TYPE_HEART_RATE_BPM);
      AGGREGATE_INPUT_TYPES.add(TYPE_POWER_SAMPLE);
      AGGREGATE_INPUT_TYPES.add(TYPE_SPEED);
      AGGREGATE_INPUT_TYPES.add(TYPE_STEP_COUNT_DELTA);
      AGGREGATE_INPUT_TYPES.add(TYPE_WEIGHT);
      CREATOR = new zzl();
   }

   public static final class zza {
      public static final DataType zzaTW;
      public static final DataType zzaTX;

      static {
         zzaTW = new DataType("com.google.internal.session.debug", new Field[]{Field.zza.zzaUF});
         zzaTX = new DataType("com.google.internal.session.v2", new Field[]{Field.zza.zzaUG});
      }
   }
}
