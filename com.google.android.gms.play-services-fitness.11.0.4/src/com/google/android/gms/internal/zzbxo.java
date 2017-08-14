package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.zzd;

final class zzbxo extends zzbus {
   // $FF: synthetic field
   private BleDevice zzaVB;

   zzbxo(zzbxk var1, GoogleApiClient var2, BleDevice var3) {
      this.zzaVB = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbup var3 = (zzbup)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwh)var3.zzrf()).zza(new zzd(this.zzaVB.getAddress(), this.zzaVB, var4));
   }
}
