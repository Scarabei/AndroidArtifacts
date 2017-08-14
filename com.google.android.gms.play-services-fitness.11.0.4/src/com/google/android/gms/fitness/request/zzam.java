package com.google.android.gms.fitness.request;

import java.util.HashMap;
import java.util.Map;

public final class zzam {
   private static final zzam zzaWT = new zzam();
   private final Map zzaWU = new HashMap();

   public static zzam zztV() {
      return zzaWT;
   }

   public final zzak zza(OnDataPointListener var1) {
      Map var2 = this.zzaWU;
      synchronized(this.zzaWU) {
         zzak var3;
         if ((var3 = (zzak)this.zzaWU.get(var1)) == null) {
            var3 = new zzak(var1, (zzal)null);
            this.zzaWU.put(var1, var3);
         }

         return var3;
      }
   }

   public final zzak zzb(OnDataPointListener var1) {
      Map var2 = this.zzaWU;
      synchronized(this.zzaWU) {
         return (zzak)this.zzaWU.get(var1);
      }
   }

   public final zzak zzc(OnDataPointListener var1) {
      Map var2 = this.zzaWU;
      synchronized(this.zzaWU) {
         zzak var3;
         return (var3 = (zzak)this.zzaWU.remove(var1)) != null ? var3 : new zzak(var1, (zzal)null);
      }
   }
}
