package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zze extends zzbr {
   private static final String ID;
   private final zza zzbDn;

   public zze(Context var1) {
      this(zza.zzbl(var1));
   }

   private zze(zza var1) {
      super(ID);
      this.zzbDn = var1;
      this.zzbDn.zzAy();
   }

   public final boolean zzAE() {
      return false;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      String var2;
      return (var2 = this.zzbDn.zzAy()) == null ? zzgk.zzCh() : zzgk.zzI(var2);
   }

   static {
      ID = zzbf.zzdm.toString();
   }
}
