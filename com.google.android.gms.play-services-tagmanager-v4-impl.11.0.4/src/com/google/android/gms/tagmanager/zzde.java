package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import java.util.Locale;
import java.util.Map;

public final class zzde extends zzbr {
   private static final String ID;

   public zzde() {
      super(ID);
   }

   public final boolean zzAE() {
      return false;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      Locale var2;
      if ((var2 = Locale.getDefault()) == null) {
         return zzgk.zzCh();
      } else {
         String var3;
         return (var3 = var2.getLanguage()) == null ? zzgk.zzCh() : zzgk.zzI(var3.toLowerCase());
      }
   }

   static {
      ID = zzbf.zzdH.toString();
   }
}
