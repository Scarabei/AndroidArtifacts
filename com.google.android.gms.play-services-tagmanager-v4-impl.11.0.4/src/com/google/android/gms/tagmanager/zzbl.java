package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzbl extends zzga {
   private static final String ID;

   public zzbl() {
      super(ID);
   }

   protected final boolean zza(String var1, String var2, Map var3) {
      return var1.endsWith(var2);
   }

   static {
      ID = zzbf.zzex.toString();
   }
}
