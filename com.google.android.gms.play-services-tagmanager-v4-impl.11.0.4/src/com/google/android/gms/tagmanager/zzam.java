package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzam extends zzbr {
   private static final String ID;
   private static final String zzbEb;
   private static final String zzbDq;
   private final zzan zzbEc;

   public zzam(zzan var1) {
      super(ID, zzbEb);
      this.zzbEc = var1;
   }

   public final boolean zzAE() {
      return false;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      String var2 = zzgk.zzb((com.google.android.gms.internal.zzbr)var1.get(zzbEb));
      HashMap var3 = new HashMap();
      com.google.android.gms.internal.zzbr var4;
      if ((var4 = (com.google.android.gms.internal.zzbr)var1.get(zzbDq)) != null) {
         Object var5;
         if (!((var5 = zzgk.zzg(var4)) instanceof Map)) {
            zzdj.zzaT("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
            return zzgk.zzCh();
         }

         Iterator var7 = ((Map)var5).entrySet().iterator();

         while(var7.hasNext()) {
            Entry var8 = (Entry)var7.next();
            var3.put(var8.getKey().toString(), var8.getValue());
         }
      }

      try {
         return zzgk.zzI(this.zzbEc.zzd(var2, var3));
      } catch (Exception var9) {
         String var6 = String.valueOf(var9.getMessage());
         zzdj.zzaT((new StringBuilder(34 + String.valueOf(var2).length() + String.valueOf(var6).length())).append("Custom macro/tag ").append(var2).append(" threw exception ").append(var6).toString());
         return zzgk.zzCh();
      }
   }

   static {
      ID = zzbf.zzdE.toString();
      zzbEb = zzbg.zzhH.toString();
      zzbDq = zzbg.zzfE.toString();
   }
}
