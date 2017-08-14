package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import java.util.Iterator;
import java.util.Map;

public abstract class zzeg extends zzbr {
   private static final String zzbEH;
   private static final String zzbFE;

   public zzeg(String var1) {
      super(var1, zzbEH, zzbFE);
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      Iterator var2 = var1.values().iterator();

      while(var2.hasNext()) {
         if ((com.google.android.gms.internal.zzbr)var2.next() == zzgk.zzCh()) {
            return zzgk.zzI(false);
         }
      }

      com.google.android.gms.internal.zzbr var4 = (com.google.android.gms.internal.zzbr)var1.get(zzbEH);
      com.google.android.gms.internal.zzbr var3 = (com.google.android.gms.internal.zzbr)var1.get(zzbFE);
      return zzgk.zzI(var4 != null && var3 != null ? this.zza(var4, var3, var1) : false);
   }

   public final boolean zzAE() {
      return true;
   }

   protected abstract boolean zza(com.google.android.gms.internal.zzbr var1, com.google.android.gms.internal.zzbr var2, Map var3);

   static {
      zzbEH = zzbg.zzfR.toString();
      zzbFE = zzbg.zzfS.toString();
   }
}
