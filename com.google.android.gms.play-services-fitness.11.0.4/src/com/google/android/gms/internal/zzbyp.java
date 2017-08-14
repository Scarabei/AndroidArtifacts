package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzbm;

final class zzbyp extends zzbvj {
   // $FF: synthetic field
   private DataType zzaVN;

   zzbyp(zzbyl var1, GoogleApiClient var2, DataType var3) {
      this.zzaVN = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvg var3 = (zzbvg)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwr)var3.zzrf()).zza(new zzbm(this.zzaVN, (DataSource)null, var4));
   }
}
