package com.google.android.gms.common.api;

import android.accounts.Account;
import android.os.Looper;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbas;
import com.google.android.gms.internal.zzbem;

public final class zzd {
   private zzbem zzaAM;
   private Looper zzrM;

   public final zzd zza(zzbem var1) {
      zzbo.zzb(var1, "StatusExceptionMapper must not be null.");
      this.zzaAM = var1;
      return this;
   }

   public final zzd zza(Looper var1) {
      zzbo.zzb(var1, "Looper must not be null.");
      this.zzrM = var1;
      return this;
   }

   public final GoogleApi.zza zzpj() {
      if (this.zzaAM == null) {
         this.zzaAM = new zzbas();
      }

      if (this.zzrM == null) {
         if (Looper.myLooper() != null) {
            this.zzrM = Looper.myLooper();
         } else {
            this.zzrM = Looper.getMainLooper();
         }
      }

      return new GoogleApi.zza(this.zzaAM, (Account)null, this.zzrM, (zzc)null);
   }
}
