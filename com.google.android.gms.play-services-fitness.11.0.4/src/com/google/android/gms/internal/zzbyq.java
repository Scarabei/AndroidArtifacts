package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzbm;

final class zzbyq extends zzbvj {
   // $FF: synthetic field
   private DataSource zzaVT;

   zzbyq(zzbyl var1, GoogleApiClient var2, DataSource var3) {
      this.zzaVT = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvg var3 = (zzbvg)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwr)var3.zzrf()).zza(new zzbm((DataType)null, this.zzaVT, var4));
   }
}
