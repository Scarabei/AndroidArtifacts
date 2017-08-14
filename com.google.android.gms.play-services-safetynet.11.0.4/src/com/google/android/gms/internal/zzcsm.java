package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.SparseArray;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.safetynet.SafeBrowsingData;

final class zzcsm extends zzcru {
   // $FF: synthetic field
   private zzcsa.zzf zzbCb;

   zzcsm(zzcsa.zzf var1) {
      this.zzbCb = var1;
      super();
   }

   public final void zza(Status var1, SafeBrowsingData var2) {
      DataHolder var3;
      if ((var3 = var2.getBlacklistsDataHolder()) != null) {
         try {
            int var4;
            if ((var4 = var3.getCount()) != 0) {
               if (zzcsa.zzbBM != null) {
                  zzcsa.zzbBM.clear();
               }

               zzcsa.zzbBM = new SparseArray();

               for(int var5 = 0; var5 < var4; ++var5) {
                  zzcsr var6 = new zzcsr(var3, var5);
                  zzcsa.zzbBM.put(var6.getThreatType(), var6);
               }

               zzcsa.zzbBN = SystemClock.elapsedRealtime();
            }
         } finally {
            if (!var3.isClosed()) {
               var3.close();
            }

         }
      }

      this.zzbCb.setResult(new zzcsa.zzi(var1, var2));
   }
}
