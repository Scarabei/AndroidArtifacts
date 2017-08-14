package com.google.android.gms.internal;

import android.content.Context;

public final class zzbha {
   private zzbgz zzaKj = null;
   private static zzbha zzaKk = new zzbha();

   private final synchronized zzbgz zzaO(Context var1) {
      if (this.zzaKj == null) {
         Context var2 = var1.getApplicationContext() == null ? var1 : var1.getApplicationContext();
         this.zzaKj = new zzbgz(var2);
      }

      return this.zzaKj;
   }

   public static zzbgz zzaP(Context var0) {
      return zzaKk.zzaO(var0);
   }
}
