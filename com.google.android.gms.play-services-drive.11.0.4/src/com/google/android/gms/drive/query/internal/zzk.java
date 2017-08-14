package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.query.Filter;
import java.util.List;

public final class zzk implements zzj {
   private Boolean zzaRq = false;

   public static boolean zza(Filter var0) {
      return var0 == null ? false : ((Boolean)var0.zza(new zzk())).booleanValue();
   }

   // $FF: synthetic method
   public final Object zztu() {
      return this.zzaRq;
   }

   // $FF: synthetic method
   public final Object zzcU(String var1) {
      if (!var1.isEmpty()) {
         this.zzaRq = true;
      }

      return this.zzaRq;
   }

   // $FF: synthetic method
   public final Object zztv() {
      return this.zzaRq;
   }

   // $FF: synthetic method
   public final Object zza(zzx var1, List var2) {
      return this.zzaRq;
   }

   // $FF: synthetic method
   public final Object zzv(Object var1) {
      return this.zzaRq;
   }

   // $FF: synthetic method
   public final Object zza(com.google.android.gms.drive.metadata.zzb var1, Object var2) {
      return this.zzaRq;
   }

   // $FF: synthetic method
   public final Object zzd(MetadataField var1, Object var2) {
      return this.zzaRq;
   }

   // $FF: synthetic method
   public final Object zza(zzx var1, MetadataField var2, Object var3) {
      return this.zzaRq;
   }

   // $FF: synthetic method
   public final Object zzd(MetadataField var1) {
      return this.zzaRq;
   }
}
