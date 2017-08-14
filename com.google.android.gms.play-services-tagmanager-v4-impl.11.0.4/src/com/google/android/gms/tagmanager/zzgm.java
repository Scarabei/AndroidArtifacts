package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Map;

final class zzgm extends zzbr {
   private static final String ID;
   private static final String zzbEH;

   public zzgm() {
      super(ID, zzbEH);
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      return zzgk.zzI(zzgk.zzb((com.google.android.gms.internal.zzbr)var1.get(zzbEH)).toUpperCase());
   }

   static {
      ID = zzbf.zzee.toString();
      zzbEH = zzbg.zzfR.toString();
   }
}
