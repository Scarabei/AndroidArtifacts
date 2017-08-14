package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

public abstract class zzae {
   private static final Object zzaHL = new Object();
   private static zzae zzaHM;

   public static zzae zzaC(Context var0) {
      Object var1 = zzaHL;
      synchronized(zzaHL) {
         if (zzaHM == null) {
            zzaHM = new zzag(var0.getApplicationContext());
         }
      }

      return zzaHM;
   }

   public final boolean zza(ComponentName var1, ServiceConnection var2, String var3) {
      return this.zza(new zzaf(var1), var2, var3);
   }

   public final void zza(String var1, String var2, ServiceConnection var3, String var4) {
      this.zzb(new zzaf(var1, var2), var3, var4);
   }

   public final void zzb(ComponentName var1, ServiceConnection var2, String var3) {
      this.zzb(new zzaf(var1), var2, var3);
   }

   protected abstract boolean zza(zzaf var1, ServiceConnection var2, String var3);

   protected abstract void zzb(zzaf var1, ServiceConnection var2, String var3);
}
