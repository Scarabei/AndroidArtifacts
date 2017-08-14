package com.google.android.gms.internal;

public enum zzana {
   zzagG,
   zzagH,
   zzagI,
   zzagJ,
   zzagK,
   zzagL;

   public static zzana zzbx(String var0) {
      if ("BATCH_BY_SESSION".equalsIgnoreCase(var0)) {
         return zzagH;
      } else if ("BATCH_BY_TIME".equalsIgnoreCase(var0)) {
         return zzagI;
      } else if ("BATCH_BY_BRUTE_FORCE".equalsIgnoreCase(var0)) {
         return zzagJ;
      } else if ("BATCH_BY_COUNT".equalsIgnoreCase(var0)) {
         return zzagK;
      } else {
         return "BATCH_BY_SIZE".equalsIgnoreCase(var0) ? zzagL : zzagG;
      }
   }
}
