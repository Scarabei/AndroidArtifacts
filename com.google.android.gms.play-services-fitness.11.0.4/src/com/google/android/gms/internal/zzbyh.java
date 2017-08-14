package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzf;
import com.google.android.gms.fitness.result.DailyTotalResult;

final class zzbyh extends zzbvb {
   // $FF: synthetic field
   private DataType zzaVN;
   // $FF: synthetic field
   private boolean zzaVO;

   zzbyh(zzbya var1, GoogleApiClient var2, DataType var3, boolean var4) {
      this.zzaVN = var3;
      this.zzaVO = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbva var3 = (zzbva)var1;
      zzbyi var4 = new zzbyi(this);
      zzf var5 = new zzf(var4, this.zzaVN, this.zzaVO);
      ((zzbwn)var3.zzrf()).zza(var5);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return DailyTotalResult.zza(var1, this.zzaVN);
   }
}
