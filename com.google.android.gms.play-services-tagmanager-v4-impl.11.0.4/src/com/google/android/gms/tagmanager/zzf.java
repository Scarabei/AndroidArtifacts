package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzf extends zzbr {
   private static final String ID;
   private final zza zzbDn;

   public zzf(Context var1) {
      this(zza.zzbl(var1));
   }

   private zzf(zza var1) {
      super(ID);
      this.zzbDn = var1;
   }

   public final boolean zzAE() {
      return false;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      return zzgk.zzI(!this.zzbDn.isLimitAdTrackingEnabled());
   }

   static {
      ID = zzbf.zzdn.toString();
   }
}
