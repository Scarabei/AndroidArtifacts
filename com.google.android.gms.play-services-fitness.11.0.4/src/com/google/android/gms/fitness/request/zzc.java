package com.google.android.gms.fitness.request;

import java.util.HashMap;
import java.util.Map;

public final class zzc {
   private static final zzc zzaWk = new zzc();
   private final Map zzaWl = new HashMap();

   public static zzc zztT() {
      return zzaWk;
   }

   public final zza zza(BleScanCallback var1) {
      Map var2 = this.zzaWl;
      synchronized(this.zzaWl) {
         zza var3;
         if ((var3 = (zza)this.zzaWl.get(var1)) == null) {
            var3 = new zza(var1, (zzb)null);
            this.zzaWl.put(var1, var3);
         }

         return var3;
      }
   }

   public final zza zzb(BleScanCallback var1) {
      Map var2 = this.zzaWl;
      synchronized(this.zzaWl) {
         zza var3;
         return (var3 = (zza)this.zzaWl.get(var1)) != null ? var3 : new zza(var1, (zzb)null);
      }
   }
}
