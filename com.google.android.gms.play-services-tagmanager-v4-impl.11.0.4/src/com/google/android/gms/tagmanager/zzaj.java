package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzaj extends zzbr {
   private static final String ID;
   private final String zzaxs;

   public zzaj(String var1) {
      super(ID);
      this.zzaxs = var1;
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      return this.zzaxs == null ? zzgk.zzCh() : zzgk.zzI(this.zzaxs);
   }

   static {
      ID = zzbf.zzdv.toString();
   }
}
