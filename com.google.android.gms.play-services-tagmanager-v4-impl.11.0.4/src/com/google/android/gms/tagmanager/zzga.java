package com.google.android.gms.tagmanager;

import java.util.Map;

abstract class zzga extends zzeg {
   public zzga(String var1) {
      super(var1);
   }

   protected final boolean zza(com.google.android.gms.internal.zzbr var1, com.google.android.gms.internal.zzbr var2, Map var3) {
      String var4 = zzgk.zzb(var1);
      String var5 = zzgk.zzb(var2);
      return var4 != zzgk.zzCg() && var5 != zzgk.zzCg() ? this.zza(var4, var5, var3) : false;
   }

   protected abstract boolean zza(String var1, String var2, Map var3);
}
