package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

final class zzbcj extends zzbcn {
   private final ArrayList zzaDv;
   // $FF: synthetic field
   private zzbcd zzaDp;

   public zzbcj(zzbcd var1, ArrayList var2) {
      this.zzaDp = var1;
      super(var1, (zzbce)null);
      this.zzaDv = var2;
   }

   @WorkerThread
   public final void zzpV() {
      zzbcd.zzd(this.zzaDp).zzaCl.zzaDG = zzbcd.zzg(this.zzaDp);
      ArrayList var1;
      int var2 = (var1 = (ArrayList)this.zzaDv).size();
      int var3 = 0;

      while(var3 < var2) {
         Object var10000 = var1.get(var3);
         ++var3;
         ((Api.zze)var10000).zza(zzbcd.zzh(this.zzaDp), zzbcd.zzd(this.zzaDp).zzaCl.zzaDG);
      }

   }
}
