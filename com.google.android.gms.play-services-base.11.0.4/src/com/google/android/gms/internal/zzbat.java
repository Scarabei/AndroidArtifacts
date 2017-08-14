package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class zzbat {
   private final boolean zzaBz;
   private final int zzaBA;
   private final Api zzayW;
   private final Api.ApiOptions zzaAJ;

   private zzbat(Api var1, Api.ApiOptions var2) {
      this.zzaBz = false;
      this.zzayW = var1;
      this.zzaAJ = var2;
      this.zzaBA = Arrays.hashCode(new Object[]{this.zzayW, this.zzaAJ});
   }

   private zzbat(Api var1) {
      this.zzaBz = true;
      this.zzayW = var1;
      this.zzaAJ = null;
      this.zzaBA = System.identityHashCode(this);
   }

   public static zzbat zza(Api var0, Api.ApiOptions var1) {
      return new zzbat(var0, var1);
   }

   public static zzbat zzb(Api var0) {
      return new zzbat(var0);
   }

   public final String zzpr() {
      return this.zzayW.getName();
   }

   public final int hashCode() {
      return this.zzaBA;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbat)) {
         return false;
      } else {
         zzbat var2 = (zzbat)var1;
         return !this.zzaBz && !var2.zzaBz && zzbe.equal(this.zzayW, var2.zzayW) && zzbe.equal(this.zzaAJ, var2.zzaAJ);
      }
   }
}
