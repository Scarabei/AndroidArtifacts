package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.zzr;
import com.google.android.gms.fitness.result.DataTypeResult;

final class zzbxu extends zzbuu {
   // $FF: synthetic field
   private String zzaVD;

   zzbxu(zzbxs var1, GoogleApiClient var2, String var3) {
      this.zzaVD = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbut var3 = (zzbut)var1;
      zzbxw var4 = new zzbxw(this, (zzbxt)null);
      ((zzbwj)var3.zzrf()).zza(new zzr(this.zzaVD, var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return DataTypeResult.zzC(var1);
   }
}
