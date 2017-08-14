package com.google.android.gms.tagmanager;

import java.util.Map;

final class zzbj extends zzcf {
   public final void zze(String var1, Map var2) {
      CustomTagProvider var3;
      if (!zzbf.zzBg().containsKey(var1)) {
         var3 = (CustomTagProvider)zzbf.zzc(var1, CustomTagProvider.class);
         zzbf.zzBg().put(var1, var3);
      } else {
         var3 = (CustomTagProvider)zzbf.zzBg().get(var1);
      }

      if (var3 != null) {
         var3.execute(var2);
      }

   }

   public final String zzf(String var1, Map var2) {
      CustomVariableProvider var3;
      if (!zzbf.zzym().containsKey(var1)) {
         var3 = (CustomVariableProvider)zzbf.zzc(var1, CustomVariableProvider.class);
         zzbf.zzym().put(var1, var3);
      } else {
         var3 = (CustomVariableProvider)zzbf.zzym().get(var1);
      }

      return var3 != null ? var3.getValue(var2) : null;
   }
}
