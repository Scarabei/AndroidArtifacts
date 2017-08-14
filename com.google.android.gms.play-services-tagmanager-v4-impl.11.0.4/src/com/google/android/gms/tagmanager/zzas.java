package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Map;

final class zzas extends zzbr {
   private static final String ID;
   private static final String NAME;
   private static final String zzbEm;
   private final DataLayer zzbDx;

   public zzas(DataLayer var1) {
      super(ID, NAME);
      this.zzbDx = var1;
   }

   public final boolean zzAE() {
      return false;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      Object var2;
      if ((var2 = this.zzbDx.get(zzgk.zzb((com.google.android.gms.internal.zzbr)var1.get(NAME)))) == null) {
         com.google.android.gms.internal.zzbr var3;
         return (var3 = (com.google.android.gms.internal.zzbr)var1.get(zzbEm)) != null ? var3 : zzgk.zzCh();
      } else {
         return zzgk.zzI(var2);
      }
   }

   static {
      ID = zzbf.zzdu.toString();
      NAME = zzbg.zzir.toString();
      zzbEm = zzbg.zzgW.toString();
   }
}
