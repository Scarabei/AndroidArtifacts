package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import java.util.Collections;

final class zzbyt extends zzbvl {
   // $FF: synthetic field
   private DataSourcesRequest zzaVU;

   zzbyt(zzbys var1, GoogleApiClient var2, DataSourcesRequest var3) {
      this.zzaVU = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvk var3 = (zzbvk)var1;
      zzbuo var4 = new zzbuo(this);
      ((zzbwt)var3.zzrf()).zza(new DataSourcesRequest(this.zzaVU, var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new DataSourcesResult(Collections.emptyList(), var1);
   }
}
