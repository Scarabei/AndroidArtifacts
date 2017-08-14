package com.google.android.gms.tagmanager;

import java.util.Map;

abstract class zzdz extends zzeg {
   public zzdz(String var1) {
      super(var1);
   }

   protected final boolean zza(com.google.android.gms.internal.zzbr var1, com.google.android.gms.internal.zzbr var2, Map var3) {
      zzgj var4 = zzgk.zzc(var1);
      zzgj var5 = zzgk.zzc(var2);
      return var4 != zzgk.zzCf() && var5 != zzgk.zzCf() ? this.zza(var4, var5, var3) : false;
   }

   protected abstract boolean zza(zzgj var1, zzgj var2, Map var3);
}
