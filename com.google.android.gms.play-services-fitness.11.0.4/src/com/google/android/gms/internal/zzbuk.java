package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzbuk {
   public static final Set zzaVr = Collections.unmodifiableSet(new HashSet(Arrays.asList("altitude", "duration", "food_item", "meal_type", "repetitions", "resistance", "resistance_type", "debug_session", "google.android.fitness.SessionV2")));
   private final Map zzaVs;
   private final Map zzaVt;
   private static final zzbuk zzaVu = new zzbuk();

   private zzbuk() {
      HashMap var1;
      (var1 = new HashMap()).put("latitude", new zzbum(-90.0D, 90.0D, (zzbul)null));
      var1.put("longitude", new zzbum(-180.0D, 180.0D, (zzbul)null));
      var1.put("accuracy", new zzbum(0.0D, 10000.0D, (zzbul)null));
      var1.put("bpm", new zzbum(0.0D, 1000.0D, (zzbul)null));
      var1.put("altitude", new zzbum(-100000.0D, 100000.0D, (zzbul)null));
      var1.put("percentage", new zzbum(0.0D, 100.0D, (zzbul)null));
      var1.put("confidence", new zzbum(0.0D, 100.0D, (zzbul)null));
      var1.put("duration", new zzbum(0.0D, 9.223372036854776E18D, (zzbul)null));
      var1.put("height", new zzbum(0.0D, 3.0D, (zzbul)null));
      var1.put("weight", new zzbum(0.0D, 1000.0D, (zzbul)null));
      var1.put("speed", new zzbum(0.0D, 11000.0D, (zzbul)null));
      this.zzaVt = Collections.unmodifiableMap(var1);
      HashMap var2;
      (var2 = new HashMap()).put("com.google.step_count.delta", zzd("steps", new zzbum(0.0D, 1.0E-8D, (zzbul)null)));
      var2.put("com.google.calories.consumed", zzd("calories", new zzbum(0.0D, 1.0E-6D, (zzbul)null)));
      var2.put("com.google.calories.expended", zzd("calories", new zzbum(0.0D, 5.555555555555555E-10D, (zzbul)null)));
      var2.put("com.google.distance.delta", zzd("distance", new zzbum(0.0D, 1.0E-7D, (zzbul)null)));
      this.zzaVs = Collections.unmodifiableMap(var2);
   }

   private static Map zzd(Object var0, Object var1) {
      HashMap var2;
      (var2 = new HashMap()).put(var0, var1);
      return var2;
   }

   final zzbum zzdg(String var1) {
      return (zzbum)this.zzaVt.get(var1);
   }

   final zzbum zzC(String var1, String var2) {
      Map var3;
      return (var3 = (Map)this.zzaVs.get(var1)) != null ? (zzbum)var3.get(var2) : null;
   }

   public static zzbuk zztR() {
      return zzaVu;
   }
}
