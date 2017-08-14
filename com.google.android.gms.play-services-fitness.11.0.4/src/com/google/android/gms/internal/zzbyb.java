package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.zzj;

final class zzbyb extends zzbvd {
   // $FF: synthetic field
   private DataSet zzaVG;
   // $FF: synthetic field
   private boolean zzaVH;

   zzbyb(zzbya var1, GoogleApiClient var2, DataSet var3, boolean var4) {
      this.zzaVG = var3;
      this.zzaVH = false;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbva var3 = (zzbva)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwn)var3.zzrf()).zza(new zzj(this.zzaVG, var4, this.zzaVH));
   }
}
