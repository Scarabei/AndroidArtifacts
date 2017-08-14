package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzdg extends zzdz {
   private static final String ID;

   public zzdg() {
      super(ID);
   }

   protected final boolean zza(zzgj var1, zzgj var2, Map var3) {
      return var1.zza(var2) < 0;
   }

   static {
      ID = zzbf.zzeA.toString();
   }
}
