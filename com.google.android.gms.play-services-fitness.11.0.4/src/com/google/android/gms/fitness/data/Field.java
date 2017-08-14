package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;

public final class Field extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Field FIELD_ACTIVITY = zzda("activity");
   public static final Field FIELD_CONFIDENCE = zzdc("confidence");
   public static final Field FIELD_ACTIVITY_CONFIDENCE = zzde("activity_confidence");
   public static final Field FIELD_STEPS = zzda("steps");
   public static final Field FIELD_STEP_LENGTH = zzdc("step_length");
   public static final Field FIELD_DURATION = zzda("duration");
   private static Field zzaUj = zzdb("duration");
   private static Field zzaUk = zzde("activity_duration");
   public static final Field zzaUl = zzde("activity_duration.ascending");
   public static final Field zzaUm = zzde("activity_duration.descending");
   public static final Field FIELD_BPM = zzdc("bpm");
   public static final Field FIELD_LATITUDE = zzdc("latitude");
   public static final Field FIELD_LONGITUDE = zzdc("longitude");
   public static final Field FIELD_ACCURACY = zzdc("accuracy");
   public static final Field FIELD_ALTITUDE;
   public static final Field FIELD_DISTANCE;
   public static final Field FIELD_HEIGHT;
   public static final Field FIELD_WEIGHT;
   public static final Field FIELD_CIRCUMFERENCE;
   public static final Field FIELD_PERCENTAGE;
   public static final Field FIELD_SPEED;
   public static final Field FIELD_RPM;
   public static final Field zzaUn;
   public static final Field FIELD_REVOLUTIONS;
   public static final Field FIELD_CALORIES;
   public static final Field FIELD_WATTS;
   public static final Field FIELD_VOLUME;
   public static final Field FIELD_MEAL_TYPE;
   public static final int MEAL_TYPE_UNKNOWN = 0;
   public static final int MEAL_TYPE_BREAKFAST = 1;
   public static final int MEAL_TYPE_LUNCH = 2;
   public static final int MEAL_TYPE_DINNER = 3;
   public static final int MEAL_TYPE_SNACK = 4;
   public static final Field FIELD_FOOD_ITEM;
   public static final Field FIELD_NUTRIENTS;
   public static final String NUTRIENT_CALORIES = "calories";
   public static final String NUTRIENT_TOTAL_FAT = "fat.total";
   public static final String NUTRIENT_SATURATED_FAT = "fat.saturated";
   public static final String NUTRIENT_UNSATURATED_FAT = "fat.unsaturated";
   public static final String NUTRIENT_POLYUNSATURATED_FAT = "fat.polyunsaturated";
   public static final String NUTRIENT_MONOUNSATURATED_FAT = "fat.monounsaturated";
   public static final String NUTRIENT_TRANS_FAT = "fat.trans";
   public static final String NUTRIENT_CHOLESTEROL = "cholesterol";
   public static final String NUTRIENT_SODIUM = "sodium";
   public static final String NUTRIENT_POTASSIUM = "potassium";
   public static final String NUTRIENT_TOTAL_CARBS = "carbs.total";
   public static final String NUTRIENT_DIETARY_FIBER = "dietary_fiber";
   public static final String NUTRIENT_SUGAR = "sugar";
   public static final String NUTRIENT_PROTEIN = "protein";
   public static final String NUTRIENT_VITAMIN_A = "vitamin_a";
   public static final String NUTRIENT_VITAMIN_C = "vitamin_c";
   public static final String NUTRIENT_CALCIUM = "calcium";
   public static final String NUTRIENT_IRON = "iron";
   public static final Field zzaUo;
   public static final Field zzaUp;
   public static final Field zzaUq;
   public static final Field zzaUr;
   public static final Field zzaUs;
   public static final Field zzaUt;
   public static final Field FIELD_EXERCISE;
   public static final Field FIELD_REPETITIONS;
   public static final Field FIELD_RESISTANCE;
   public static final Field FIELD_RESISTANCE_TYPE;
   public static final int RESISTANCE_TYPE_UNKNOWN = 0;
   public static final int RESISTANCE_TYPE_BARBELL = 1;
   public static final int RESISTANCE_TYPE_CABLE = 2;
   public static final int RESISTANCE_TYPE_DUMBBELL = 3;
   public static final int RESISTANCE_TYPE_KETTLEBELL = 4;
   public static final int RESISTANCE_TYPE_MACHINE = 5;
   public static final int RESISTANCE_TYPE_BODY = 6;
   public static final Field FIELD_NUM_SEGMENTS;
   public static final Field FIELD_AVERAGE;
   public static final Field FIELD_MAX;
   public static final Field FIELD_MIN;
   public static final Field FIELD_LOW_LATITUDE;
   public static final Field FIELD_LOW_LONGITUDE;
   public static final Field FIELD_HIGH_LATITUDE;
   public static final Field FIELD_HIGH_LONGITUDE;
   public static final Field FIELD_OCCURRENCES;
   public static final Field zzaUu;
   public static final Field zzaUv;
   public static final Field zzaUw;
   public static final Field zzaUx;
   public static final Field zzaUy;
   public static final Field zzaUz;
   public static final Field zzaUA;
   public static final int FORMAT_INT32 = 1;
   public static final int FORMAT_FLOAT = 2;
   public static final int FORMAT_STRING = 3;
   public static final int FORMAT_MAP = 4;
   private final int versionCode;
   private final String name;
   private final int format;
   private final Boolean zzaUB;
   public static final Creator CREATOR;

   public static Field zzm(String var0, int var1) {
      byte var5 = -1;
      switch(var0.hashCode()) {
      case -2131707655:
         if (var0.equals("accuracy")) {
            var5 = 0;
         }
         break;
      case -2083865430:
         if (var0.equals("debug_session")) {
            var5 = 92;
         }
         break;
      case -2006370880:
         if (var0.equals("body_temperature_measurement_location")) {
            var5 = 21;
         }
         break;
      case -1992012396:
         if (var0.equals("duration")) {
            var5 = 32;
         }
         break;
      case -1859447186:
         if (var0.equals("blood_glucose_level")) {
            var5 = 8;
         }
         break;
      case -1655966961:
         if (var0.equals("activity")) {
            var5 = 1;
         }
         break;
      case -1595712862:
         if (var0.equals("cervical_dilation")) {
            var5 = 24;
         }
         break;
      case -1579612127:
         if (var0.equals("floor.gain")) {
            var5 = 37;
         }
         break;
      case -1579449403:
         if (var0.equals("floor.loss")) {
            var5 = 38;
         }
         break;
      case -1569430471:
         if (var0.equals("num_segments")) {
            var5 = 54;
         }
         break;
      case -1531570079:
         if (var0.equals("elevation.change")) {
            var5 = 33;
         }
         break;
      case -1440707631:
         if (var0.equals("oxygen_saturation")) {
            var5 = 58;
         }
         break;
      case -1439978388:
         if (var0.equals("latitude")) {
            var5 = 44;
         }
         break;
      case -1352492506:
         if (var0.equals("num_dimensions")) {
            var5 = 52;
         }
         break;
      case -1271636505:
         if (var0.equals("floors")) {
            var5 = 39;
         }
         break;
      case -1248595573:
         if (var0.equals("supplemental_oxygen_flow_rate_average")) {
            var5 = 71;
         }
         break;
      case -1221029593:
         if (var0.equals("height")) {
            var5 = 41;
         }
         break;
      case -1220952307:
         if (var0.equals("blood_pressure_measurement_location")) {
            var5 = 14;
         }
         break;
      case -1133736764:
         if (var0.equals("activity_duration")) {
            var5 = 3;
         }
         break;
      case -1129337776:
         if (var0.equals("num_samples")) {
            var5 = 53;
         }
         break;
      case -1110756780:
         if (var0.equals("food_item")) {
            var5 = 40;
         }
         break;
      case -921832806:
         if (var0.equals("percentage")) {
            var5 = 74;
         }
         break;
      case -918978307:
         if (var0.equals("cervical_position")) {
            var5 = 28;
         }
         break;
      case -810883302:
         if (var0.equals("volume")) {
            var5 = 86;
         }
         break;
      case -803244749:
         if (var0.equals("blood_pressure_systolic")) {
            var5 = 15;
         }
         break;
      case -791592328:
         if (var0.equals("weight")) {
            var5 = 88;
         }
         break;
      case -631448035:
         if (var0.equals("average")) {
            var5 = 7;
         }
         break;
      case -626344110:
         if (var0.equals("high_longitude")) {
            var5 = 43;
         }
         break;
      case -619868540:
         if (var0.equals("low_longitude")) {
            var5 = 47;
         }
         break;
      case -511934137:
         if (var0.equals("sensor_values")) {
            var5 = 69;
         }
         break;
      case -494782871:
         if (var0.equals("high_latitude")) {
            var5 = 42;
         }
         break;
      case -452643911:
         if (var0.equals("step_length")) {
            var5 = 82;
         }
         break;
      case -437053898:
         if (var0.equals("meal_type")) {
            var5 = 49;
         }
         break;
      case -277306353:
         if (var0.equals("circumference")) {
            var5 = 29;
         }
         break;
      case -266093204:
         if (var0.equals("nutrients")) {
            var5 = 55;
         }
         break;
      case -228366862:
         if (var0.equals("oxygen_saturation_measurement_method")) {
            var5 = 61;
         }
         break;
      case -168965370:
         if (var0.equals("calories")) {
            var5 = 23;
         }
         break;
      case -126538880:
         if (var0.equals("resistance_type")) {
            var5 = 77;
         }
         break;
      case -28590302:
         if (var0.equals("ovulation_test_result")) {
            var5 = 57;
         }
         break;
      case 120:
         if (var0.equals("x")) {
            var5 = 89;
         }
         break;
      case 121:
         if (var0.equals("y")) {
            var5 = 90;
         }
         break;
      case 122:
         if (var0.equals("z")) {
            var5 = 91;
         }
         break;
      case 97759:
         if (var0.equals("bpm")) {
            var5 = 22;
         }
         break;
      case 107876:
         if (var0.equals("max")) {
            var5 = 48;
         }
         break;
      case 108114:
         if (var0.equals("min")) {
            var5 = 51;
         }
         break;
      case 113135:
         if (var0.equals("rpm")) {
            var5 = 79;
         }
         break;
      case 66639641:
         if (var0.equals("temporal_relation_to_sleep")) {
            var5 = 85;
         }
         break;
      case 109641799:
         if (var0.equals("speed")) {
            var5 = 80;
         }
         break;
      case 109761319:
         if (var0.equals("steps")) {
            var5 = 81;
         }
         break;
      case 112903913:
         if (var0.equals("watts")) {
            var5 = 87;
         }
         break;
      case 120904628:
         if (var0.equals("sensor_types")) {
            var5 = 68;
         }
         break;
      case 137365935:
         if (var0.equals("longitude")) {
            var5 = 45;
         }
         break;
      case 198162679:
         if (var0.equals("low_latitude")) {
            var5 = 46;
         }
         break;
      case 220648413:
         if (var0.equals("blood_pressure_diastolic_average")) {
            var5 = 11;
         }
         break;
      case 248891292:
         if (var0.equals("blood_glucose_specimen_source")) {
            var5 = 9;
         }
         break;
      case 286612066:
         if (var0.equals("activity_duration.descending")) {
            var5 = 5;
         }
         break;
      case 288459765:
         if (var0.equals("distance")) {
            var5 = 31;
         }
         break;
      case 306600408:
         if (var0.equals("google.android.fitness.SessionV2")) {
            var5 = 93;
         }
         break;
      case 320627489:
         if (var0.equals("cervical_mucus_texture")) {
            var5 = 27;
         }
         break;
      case 455965230:
         if (var0.equals("activity_duration.ascending")) {
            var5 = 4;
         }
         break;
      case 475560024:
         if (var0.equals("blood_pressure_systolic_max")) {
            var5 = 17;
         }
         break;
      case 475560262:
         if (var0.equals("blood_pressure_systolic_min")) {
            var5 = 18;
         }
         break;
      case 581888402:
         if (var0.equals("cervical_mucus_amount")) {
            var5 = 26;
         }
         break;
      case 623947695:
         if (var0.equals("oxygen_saturation_average")) {
            var5 = 59;
         }
         break;
      case 738210934:
         if (var0.equals("google.android.fitness.StrideModel")) {
            var5 = 83;
         }
         break;
      case 784486594:
         if (var0.equals("occurrences")) {
            var5 = 56;
         }
         break;
      case 811264586:
         if (var0.equals("revolutions")) {
            var5 = 78;
         }
         break;
      case 815736413:
         if (var0.equals("oxygen_saturation_system")) {
            var5 = 63;
         }
         break;
      case 829251210:
         if (var0.equals("confidence")) {
            var5 = 30;
         }
         break;
      case 833248065:
         if (var0.equals("temporal_relation_to_meal")) {
            var5 = 84;
         }
         break;
      case 883161687:
         if (var0.equals("body_temperature")) {
            var5 = 20;
         }
         break;
      case 984367650:
         if (var0.equals("repetitions")) {
            var5 = 75;
         }
         break;
      case 998412730:
         if (var0.equals("activity_confidence")) {
            var5 = 2;
         }
         break;
      case 1136011766:
         if (var0.equals("sample_period")) {
            var5 = 66;
         }
         break;
      case 1276952063:
         if (var0.equals("blood_pressure_diastolic")) {
            var5 = 10;
         }
         break;
      case 1284575222:
         if (var0.equals("oxygen_saturation_max")) {
            var5 = 60;
         }
         break;
      case 1284575460:
         if (var0.equals("oxygen_saturation_min")) {
            var5 = 62;
         }
         break;
      case 1403812644:
         if (var0.equals("blood_pressure_diastolic_max")) {
            var5 = 12;
         }
         break;
      case 1403812882:
         if (var0.equals("blood_pressure_diastolic_min")) {
            var5 = 13;
         }
         break;
      case 1527920799:
         if (var0.equals("sensor_type")) {
            var5 = 67;
         }
         break;
      case 1708915229:
         if (var0.equals("timestamps")) {
            var5 = 65;
         }
         break;
      case 1857734768:
         if (var0.equals("elevation.gain")) {
            var5 = 34;
         }
         break;
      case 1857897492:
         if (var0.equals("elevation.loss")) {
            var5 = 35;
         }
         break;
      case 1863800889:
         if (var0.equals("resistance")) {
            var5 = 76;
         }
         break;
      case 1880897007:
         if (var0.equals("oxygen_therapy_administration_mode")) {
            var5 = 64;
         }
         break;
      case 1892583496:
         if (var0.equals("menstrual_flow")) {
            var5 = 50;
         }
         break;
      case 1958191058:
         if (var0.equals("supplemental_oxygen_flow_rate_max")) {
            var5 = 72;
         }
         break;
      case 1958191296:
         if (var0.equals("supplemental_oxygen_flow_rate_min")) {
            var5 = 73;
         }
         break;
      case 1983072038:
         if (var0.equals("body_position")) {
            var5 = 19;
         }
         break;
      case 2020153105:
         if (var0.equals("blood_pressure_systolic_average")) {
            var5 = 16;
         }
         break;
      case 2036550306:
         if (var0.equals("altitude")) {
            var5 = 6;
         }
         break;
      case 2056323544:
         if (var0.equals("exercise")) {
            var5 = 36;
         }
         break;
      case 2072582505:
         if (var0.equals("cervical_firmness")) {
            var5 = 25;
         }
         break;
      case 2078370221:
         if (var0.equals("supplemental_oxygen_flow_rate")) {
            var5 = 70;
         }
      }

      switch(var5) {
      case 0:
         return FIELD_ACCURACY;
      case 1:
         return FIELD_ACTIVITY;
      case 2:
         return FIELD_ACTIVITY_CONFIDENCE;
      case 3:
         return zzaUk;
      case 4:
         return zzaUl;
      case 5:
         return zzaUm;
      case 6:
         return FIELD_ALTITUDE;
      case 7:
         return FIELD_AVERAGE;
      case 8:
         return HealthFields.FIELD_BLOOD_GLUCOSE_LEVEL;
      case 9:
         return HealthFields.FIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE;
      case 10:
         return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC;
      case 11:
         return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_AVERAGE;
      case 12:
         return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MAX;
      case 13:
         return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MIN;
      case 14:
         return HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION;
      case 15:
         return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC;
      case 16:
         return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_AVERAGE;
      case 17:
         return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MAX;
      case 18:
         return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MIN;
      case 19:
         return HealthFields.FIELD_BODY_POSITION;
      case 20:
         return HealthFields.FIELD_BODY_TEMPERATURE;
      case 21:
         return HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION;
      case 22:
         return FIELD_BPM;
      case 23:
         return FIELD_CALORIES;
      case 24:
         return HealthFields.FIELD_CERVICAL_DILATION;
      case 25:
         return HealthFields.FIELD_CERVICAL_FIRMNESS;
      case 26:
         return HealthFields.FIELD_CERVICAL_MUCUS_AMOUNT;
      case 27:
         return HealthFields.FIELD_CERVICAL_MUCUS_TEXTURE;
      case 28:
         return HealthFields.FIELD_CERVICAL_POSITION;
      case 29:
         return FIELD_CIRCUMFERENCE;
      case 30:
         return FIELD_CONFIDENCE;
      case 31:
         return FIELD_DISTANCE;
      case 32:
         return FIELD_DURATION;
      case 33:
         return zzaUo;
      case 34:
         return zzaUp;
      case 35:
         return zzaUq;
      case 36:
         return FIELD_EXERCISE;
      case 37:
         return zzaUs;
      case 38:
         return zzaUt;
      case 39:
         return zzaUr;
      case 40:
         return FIELD_FOOD_ITEM;
      case 41:
         return FIELD_HEIGHT;
      case 42:
         return FIELD_HIGH_LATITUDE;
      case 43:
         return FIELD_HIGH_LONGITUDE;
      case 44:
         return FIELD_LATITUDE;
      case 45:
         return FIELD_LONGITUDE;
      case 46:
         return FIELD_LOW_LATITUDE;
      case 47:
         return FIELD_LOW_LONGITUDE;
      case 48:
         return FIELD_MAX;
      case 49:
         return FIELD_MEAL_TYPE;
      case 50:
         return HealthFields.FIELD_MENSTRUAL_FLOW;
      case 51:
         return FIELD_MIN;
      case 52:
         return zzaUz;
      case 53:
         return zzaUy;
      case 54:
         return FIELD_NUM_SEGMENTS;
      case 55:
         return FIELD_NUTRIENTS;
      case 56:
         return FIELD_OCCURRENCES;
      case 57:
         return HealthFields.FIELD_OVULATION_TEST_RESULT;
      case 58:
         return HealthFields.FIELD_OXYGEN_SATURATION;
      case 59:
         return HealthFields.FIELD_OXYGEN_SATURATION_AVERAGE;
      case 60:
         return HealthFields.FIELD_OXYGEN_SATURATION_MAX;
      case 61:
         return HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD;
      case 62:
         return HealthFields.FIELD_OXYGEN_SATURATION_MIN;
      case 63:
         return HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM;
      case 64:
         return HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE;
      case 65:
         return zzaUw;
      case 66:
         return zzaUx;
      case 67:
         return zzaUu;
      case 68:
         return zzaUv;
      case 69:
         return zzaUA;
      case 70:
         return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE;
      case 71:
         return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE;
      case 72:
         return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX;
      case 73:
         return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN;
      case 74:
         return FIELD_PERCENTAGE;
      case 75:
         return FIELD_REPETITIONS;
      case 76:
         return FIELD_RESISTANCE;
      case 77:
         return FIELD_RESISTANCE_TYPE;
      case 78:
         return FIELD_REVOLUTIONS;
      case 79:
         return FIELD_RPM;
      case 80:
         return FIELD_SPEED;
      case 81:
         return FIELD_STEPS;
      case 82:
         return FIELD_STEP_LENGTH;
      case 83:
         return zzaUn;
      case 84:
         return HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL;
      case 85:
         return HealthFields.FIELD_TEMPORAL_RELATION_TO_SLEEP;
      case 86:
         return FIELD_VOLUME;
      case 87:
         return FIELD_WATTS;
      case 88:
         return FIELD_WEIGHT;
      case 89:
         return Field.zza.zzaUC;
      case 90:
         return Field.zza.zzaUD;
      case 91:
         return Field.zza.zzaUE;
      case 92:
         return Field.zza.zzaUF;
      case 93:
         return Field.zza.zzaUG;
      default:
         return new Field(var0, var1, (Boolean)null);
      }
   }

   private Field(String var1, int var2) {
      this(2, var1, var2, (Boolean)null);
   }

   private Field(String var1, int var2, Boolean var3) {
      this(2, var1, var2, var3);
   }

   private static Field zzda(String var0) {
      return new Field(var0, 1);
   }

   static Field zzdb(String var0) {
      return new Field(var0, 1, true);
   }

   static Field zzdc(String var0) {
      return new Field(var0, 2);
   }

   private static Field zzdd(String var0) {
      return new Field(var0, 3);
   }

   private static Field zzde(String var0) {
      return new Field(var0, 4);
   }

   static Field zzdf(String var0) {
      return new Field(var0, 7, true);
   }

   Field(int var1, String var2, int var3, Boolean var4) {
      this.versionCode = var1;
      this.name = (String)zzbo.zzu(var2);
      this.format = var3;
      this.zzaUB = var4;
   }

   public final String getName() {
      return this.name;
   }

   public final int getFormat() {
      return this.format;
   }

   public final Boolean isOptional() {
      return this.zzaUB;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         if (var1 instanceof Field) {
            Field var3 = (Field)var1;
            if (this.name.equals(var3.name) && this.format == var3.format) {
               return true;
            }
         }

         return false;
      }
   }

   public final int hashCode() {
      return this.name.hashCode();
   }

   public final String toString() {
      return String.format("%s(%s)", this.name, this.format == 1 ? "i" : "f");
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getFormat());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.isOptional(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static {
      String var0 = "altitude";
      FIELD_ALTITUDE = new Field(var0, 2, true);
      FIELD_DISTANCE = zzdc("distance");
      FIELD_HEIGHT = zzdc("height");
      FIELD_WEIGHT = zzdc("weight");
      FIELD_CIRCUMFERENCE = zzdc("circumference");
      FIELD_PERCENTAGE = zzdc("percentage");
      FIELD_SPEED = zzdc("speed");
      FIELD_RPM = zzdc("rpm");
      var0 = "google.android.fitness.StrideModel";
      zzaUn = new Field(var0, 7);
      FIELD_REVOLUTIONS = zzda("revolutions");
      FIELD_CALORIES = zzdc("calories");
      FIELD_WATTS = zzdc("watts");
      FIELD_VOLUME = zzdc("volume");
      FIELD_MEAL_TYPE = zzda("meal_type");
      FIELD_FOOD_ITEM = zzdd("food_item");
      FIELD_NUTRIENTS = zzde("nutrients");
      zzaUo = zzdc("elevation.change");
      zzaUp = zzde("elevation.gain");
      zzaUq = zzde("elevation.loss");
      zzaUr = zzdc("floors");
      zzaUs = zzde("floor.gain");
      zzaUt = zzde("floor.loss");
      FIELD_EXERCISE = zzdd("exercise");
      FIELD_REPETITIONS = zzda("repetitions");
      FIELD_RESISTANCE = zzdc("resistance");
      FIELD_RESISTANCE_TYPE = zzda("resistance_type");
      FIELD_NUM_SEGMENTS = zzda("num_segments");
      FIELD_AVERAGE = zzdc("average");
      FIELD_MAX = zzdc("max");
      FIELD_MIN = zzdc("min");
      FIELD_LOW_LATITUDE = zzdc("low_latitude");
      FIELD_LOW_LONGITUDE = zzdc("low_longitude");
      FIELD_HIGH_LATITUDE = zzdc("high_latitude");
      FIELD_HIGH_LONGITUDE = zzdc("high_longitude");
      FIELD_OCCURRENCES = zzda("occurrences");
      zzaUu = zzda("sensor_type");
      zzaUv = zzda("sensor_types");
      var0 = "timestamps";
      zzaUw = new Field(var0, 5);
      zzaUx = zzda("sample_period");
      zzaUy = zzda("num_samples");
      zzaUz = zzda("num_dimensions");
      var0 = "sensor_values";
      zzaUA = new Field(var0, 6);
      CREATOR = new zzq();
   }

   public static class zza {
      public static final Field zzaUC = Field.zzdc("x");
      public static final Field zzaUD = Field.zzdc("y");
      public static final Field zzaUE = Field.zzdc("z");
      public static final Field zzaUF = Field.zzdf("debug_session");
      public static final Field zzaUG = Field.zzdf("google.android.fitness.SessionV2");
   }
}
