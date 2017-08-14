package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

final class zzbyg extends zzbvb {
   // $FF: synthetic field
   private DataReadRequest zzaVM;

   zzbyg(zzbya var1, GoogleApiClient var2, DataReadRequest var3) {
      this.zzaVM = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbva var3 = (zzbva)var1;
      zzbyj var4 = new zzbyj(this, (zzbyb)null);
      ((zzbwn)var3.zzrf()).zza(new DataReadRequest(this.zzaVM, var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return DataReadResult.zza(var1, this.zzaVM.getDataTypes(), this.zzaVM.getDataSources());
   }
}
