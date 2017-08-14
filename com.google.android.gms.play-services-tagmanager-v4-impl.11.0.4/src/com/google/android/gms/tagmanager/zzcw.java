package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Map;

final class zzcw extends zzbr {
   private static final String ID;
   private static final String zzbDo;
   private final Context zzqD;

   public zzcw(Context var1) {
      super(ID);
      this.zzqD = var1;
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      String var2 = (com.google.android.gms.internal.zzbr)var1.get(zzbDo) != null ? zzgk.zzb((com.google.android.gms.internal.zzbr)var1.get(zzbDo)) : null;
      String var3;
      return (var3 = zzcx.zzL(this.zzqD, var2)) != null ? zzgk.zzI(var3) : zzgk.zzCh();
   }

   static {
      ID = zzbf.zzdY.toString();
      zzbDo = zzbg.zzgu.toString();
   }
}
