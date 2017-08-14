package com.google.android.gms.fitness.data;

import com.google.android.gms.internal.zzbug;
import com.google.android.gms.internal.zzbuh;
import java.util.concurrent.TimeUnit;

final class zzf implements zzbug {
   public static final zzf zzaTt = new zzf();

   public final zzbuh zztF() {
      return zzg.zzaTu;
   }

   // $FF: synthetic method
   public final double zzb(Object var1, int var2) {
      return (double)((DataPoint)var1).zzaT(var2).asFloat();
   }

   // $FF: synthetic method
   public final int zzc(Object var1, int var2) {
      return ((DataPoint)var1).zzaT(var2).asInt();
   }

   // $FF: synthetic method
   public final boolean zzd(Object var1, int var2) {
      return ((DataPoint)var1).zzaT(var2).isSet();
   }

   // $FF: synthetic method
   public final long zza(Object var1, TimeUnit var2) {
      DataPoint var3;
      return (var3 = (DataPoint)var1).getEndTime(var2) - var3.getStartTime(var2);
   }

   // $FF: synthetic method
   public final Object zzx(Object var1) {
      return ((DataPoint)var1).getDataType();
   }

   // $FF: synthetic method
   public final String zzy(Object var1) {
      return ((DataPoint)var1).getDataType().getName();
   }
}
