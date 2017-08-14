package com.google.android.gms.fitness.data;

import com.google.android.gms.internal.zzbuh;

final class zzg implements zzbuh {
   public static final zzg zzaTu = new zzg();

   public final boolean zzcY(String var1) {
      return zzm.zzcZ(var1) != null;
   }

   private static Field zza(DataType var0, int var1) {
      return (Field)var0.getFields().get(var1);
   }

   // $FF: synthetic method
   public final int zze(Object var1, int var2) {
      return zza((DataType)var1, var2).getFormat();
   }

   // $FF: synthetic method
   public final boolean zzf(Object var1, int var2) {
      DataType var3 = (DataType)var1;
      return Boolean.TRUE.equals(zza(var3, var2).isOptional());
   }

   // $FF: synthetic method
   public final String zzg(Object var1, int var2) {
      return zza((DataType)var1, var2).getName();
   }

   // $FF: synthetic method
   public final int zzz(Object var1) {
      return ((DataType)var1).getFields().size();
   }

   // $FF: synthetic method
   public final String zzA(Object var1) {
      return ((DataType)var1).getName();
   }
}
