package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzj;
import java.lang.ref.WeakReference;

final class zzbcf implements zzj {
   private final WeakReference zzaDq;
   private final Api zzayW;
   private final boolean zzaCj;

   public zzbcf(zzbcd var1, Api var2, boolean var3) {
      this.zzaDq = new WeakReference(var1);
      this.zzayW = var2;
      this.zzaCj = var3;
   }

   public final void zzf(@NonNull ConnectionResult var1) {
      zzbcd var2;
      if ((var2 = (zzbcd)this.zzaDq.get()) != null) {
         zzbo.zza(Looper.myLooper() == zzbcd.zzd(var2).zzaCl.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
         zzbcd.zzc(var2).lock();

         try {
            if (!zzbcd.zza(var2, 0)) {
               return;
            }

            if (!var1.isSuccess()) {
               zzbcd.zza(var2, var1, this.zzayW, this.zzaCj);
            }

            if (zzbcd.zzk(var2)) {
               zzbcd.zzj(var2);
            }
         } finally {
            zzbcd.zzc(var2).unlock();
         }

      }
   }

   // $FF: synthetic method
   static boolean zza(zzbcf var0) {
      return var0.zzaCj;
   }
}
