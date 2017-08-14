package com.google.android.gms.common.util;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbo;
import java.util.Set;

public final class zzs {
   public static String[] zzc(Set var0) {
      zzbo.zzb(var0, "scopes can't be null.");
      Scope[] var1;
      zzbo.zzb(var1 = (Scope[])var0.toArray(new Scope[var0.size()]), "scopes can't be null.");
      String[] var2 = new String[var1.length];

      for(int var3 = 0; var3 < var1.length; ++var3) {
         var2[var3] = var1[var3].zzpp();
      }

      return var2;
   }
}
