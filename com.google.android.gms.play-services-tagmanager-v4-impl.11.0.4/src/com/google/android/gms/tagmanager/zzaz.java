package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzaz extends zzgi {
   private static final String ID;
   private static final String VALUE;
   private static final String zzbEx;
   private final DataLayer zzbDx;

   public zzaz(DataLayer var1) {
      super(ID, VALUE);
      this.zzbDx = var1;
   }

   public final void zzq(Map var1) {
      com.google.android.gms.internal.zzbr var3 = (com.google.android.gms.internal.zzbr)var1.get(VALUE);
      zzaz var2 = this;
      Object var4;
      if (var3 != null && var3 != zzgk.zzCb() && (var4 = zzgk.zzg(var3)) instanceof List) {
         Iterator var5 = ((List)var4).iterator();

         while(var5.hasNext()) {
            Object var6;
            if ((var6 = var5.next()) instanceof Map) {
               Map var7 = (Map)var6;
               var2.zzbDx.push(var7);
            }
         }
      }

      var3 = (com.google.android.gms.internal.zzbr)var1.get(zzbEx);
      if (var3 != null && var3 != zzgk.zzCb()) {
         String var8;
         if ((var8 = zzgk.zzb(var3)) != zzgk.zzCg()) {
            this.zzbDx.zzfc(var8);
         }

      }
   }

   static {
      ID = zzbf.zzec.toString();
      VALUE = zzbg.zzko.toString();
      zzbEx = zzbg.zzgp.toString();
   }
}
