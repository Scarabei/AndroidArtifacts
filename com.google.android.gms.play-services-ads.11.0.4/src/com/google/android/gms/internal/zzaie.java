package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import java.util.Map;

@zzzn
public final class zzaie {
   private static zzs zzaad;
   private static final Object zzaae = new Object();
   private static zzaii zzaaf = new zzaif();

   public zzaie(Context var1) {
      zzU(var1);
   }

   public final zzajm zza(String var1, zzaii var2) {
      zzain var3 = new zzain(this, (zzaif)null);
      zzaad.zzc(new zzaij(var1, var2, var3));
      return var3;
   }

   public final zzajm zza(int var1, String var2, @Nullable Map var3, @Nullable byte[] var4) {
      zzain var5 = new zzain(this, (zzaif)null);
      zzaig var6 = new zzaig(this, var2, var5);
      zzaih var7 = new zzaih(this, var1, var2, var5, var6, var4, var3);
      zzaad.zzc(var7);
      return var5;
   }

   public final zzajm zzb(String var1, Map var2) {
      return this.zza(0, var1, var2, (byte[])null);
   }

   private static zzs zzU(Context var0) {
      Object var1 = zzaae;
      synchronized(zzaae) {
         if (zzaad == null) {
            zzaad = zzas.zza(var0.getApplicationContext(), (zzan)null);
         }

         return zzaad;
      }
   }
}
