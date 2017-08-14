package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

final class zzbxt extends zzbuu {
   // $FF: synthetic field
   private DataTypeCreateRequest zzaVC;

   zzbxt(zzbxs var1, GoogleApiClient var2, DataTypeCreateRequest var3) {
      this.zzaVC = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbut var3 = (zzbut)var1;
      zzbxw var4 = new zzbxw(this, (zzbxt)null);
      ((zzbwj)var3.zzrf()).zza(new DataTypeCreateRequest(this.zzaVC, var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return DataTypeResult.zzC(var1);
   }
}
