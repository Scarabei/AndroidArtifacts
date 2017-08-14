package com.google.android.gms.fitness;

public final class zza {
   private static final String[] zzaSX;

   public static int zzcW(String var0) {
      for(int var1 = 0; var1 < zzaSX.length; ++var1) {
         if (zzaSX[var1].equals(var0)) {
            return var1;
         }
      }

      return 4;
   }

   public static String getName(int var0) {
      if (var0 >= 0 && var0 < zzaSX.length) {
         String var1;
         return (var1 = zzaSX[var0]) == null ? "unknown" : var1;
      } else {
         return "unknown";
      }
   }

   static {
      (zzaSX = new String[121])[9] = "aerobics";
      zzaSX[119] = "archery";
      zzaSX[10] = "badminton";
      zzaSX[11] = "baseball";
      zzaSX[12] = "basketball";
      zzaSX[13] = "biathlon";
      zzaSX[1] = "biking";
      zzaSX[14] = "biking.hand";
      zzaSX[15] = "biking.mountain";
      zzaSX[16] = "biking.road";
      zzaSX[17] = "biking.spinning";
      zzaSX[18] = "biking.stationary";
      zzaSX[19] = "biking.utility";
      zzaSX[20] = "boxing";
      zzaSX[21] = "calisthenics";
      zzaSX[22] = "circuit_training";
      zzaSX[23] = "cricket";
      zzaSX[113] = "crossfit";
      zzaSX[106] = "curling";
      zzaSX[24] = "dancing";
      zzaSX[102] = "diving";
      zzaSX[117] = "elevator";
      zzaSX[25] = "elliptical";
      zzaSX[103] = "ergometer";
      zzaSX[118] = "escalator";
      zzaSX[6] = "exiting_vehicle";
      zzaSX[26] = "fencing";
      zzaSX[27] = "football.american";
      zzaSX[28] = "football.australian";
      zzaSX[29] = "football.soccer";
      zzaSX[30] = "frisbee_disc";
      zzaSX[31] = "gardening";
      zzaSX[32] = "golf";
      zzaSX[33] = "gymnastics";
      zzaSX[34] = "handball";
      zzaSX[114] = "interval_training.high_intensity";
      zzaSX[35] = "hiking";
      zzaSX[36] = "hockey";
      zzaSX[37] = "horseback_riding";
      zzaSX[38] = "housework";
      zzaSX[104] = "ice_skating";
      zzaSX[0] = "in_vehicle";
      zzaSX[115] = "interval_training";
      zzaSX[39] = "jump_rope";
      zzaSX[40] = "kayaking";
      zzaSX[41] = "kettlebell_training";
      zzaSX[107] = "kick_scooter";
      zzaSX[42] = "kickboxing";
      zzaSX[43] = "kitesurfing";
      zzaSX[44] = "martial_arts";
      zzaSX[45] = "meditation";
      zzaSX[46] = "martial_arts.mixed";
      zzaSX[2] = "on_foot";
      zzaSX[108] = "other";
      zzaSX[47] = "p90x";
      zzaSX[48] = "paragliding";
      zzaSX[49] = "pilates";
      zzaSX[50] = "polo";
      zzaSX[51] = "racquetball";
      zzaSX[52] = "rock_climbing";
      zzaSX[53] = "rowing";
      zzaSX[54] = "rowing.machine";
      zzaSX[55] = "rugby";
      zzaSX[8] = "running";
      zzaSX[56] = "running.jogging";
      zzaSX[57] = "running.sand";
      zzaSX[58] = "running.treadmill";
      zzaSX[59] = "sailing";
      zzaSX[60] = "scuba_diving";
      zzaSX[61] = "skateboarding";
      zzaSX[62] = "skating";
      zzaSX[63] = "skating.cross";
      zzaSX[105] = "skating.indoor";
      zzaSX[64] = "skating.inline";
      zzaSX[65] = "skiing";
      zzaSX[66] = "skiing.back_country";
      zzaSX[67] = "skiing.cross_country";
      zzaSX[68] = "skiing.downhill";
      zzaSX[69] = "skiing.kite";
      zzaSX[70] = "skiing.roller";
      zzaSX[71] = "sledding";
      zzaSX[72] = "sleep";
      zzaSX[109] = "sleep.light";
      zzaSX[110] = "sleep.deep";
      zzaSX[111] = "sleep.rem";
      zzaSX[112] = "sleep.awake";
      zzaSX[73] = "snowboarding";
      zzaSX[74] = "snowmobile";
      zzaSX[75] = "snowshoeing";
      zzaSX[120] = "softball";
      zzaSX[76] = "squash";
      zzaSX[77] = "stair_climbing";
      zzaSX[78] = "stair_climbing.machine";
      zzaSX[79] = "standup_paddleboarding";
      zzaSX[3] = "still";
      zzaSX[80] = "strength_training";
      zzaSX[81] = "surfing";
      zzaSX[82] = "swimming";
      zzaSX[83] = "swimming.pool";
      zzaSX[84] = "swimming.open_water";
      zzaSX[85] = "table_tennis";
      zzaSX[86] = "team_sports";
      zzaSX[87] = "tennis";
      zzaSX[5] = "tilting";
      zzaSX[88] = "treadmill";
      zzaSX[4] = "unknown";
      zzaSX[89] = "volleyball";
      zzaSX[90] = "volleyball.beach";
      zzaSX[91] = "volleyball.indoor";
      zzaSX[92] = "wakeboarding";
      zzaSX[7] = "walking";
      zzaSX[93] = "walking.fitness";
      zzaSX[94] = "walking.nordic";
      zzaSX[95] = "walking.treadmill";
      zzaSX[116] = "walking.stroller";
      zzaSX[96] = "water_polo";
      zzaSX[97] = "weightlifting";
      zzaSX[98] = "wheelchair";
      zzaSX[99] = "windsurfing";
      zzaSX[100] = "yoga";
      zzaSX[101] = "zumba";
   }
}
