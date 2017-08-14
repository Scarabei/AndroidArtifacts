package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.panorama.Panorama;

abstract class zzcqk extends zzbay {
   protected zzcqk(GoogleApiClient var1) {
      super(Panorama.zzajR, var1);
   }

   protected abstract void zza(Context var1, zzcqc var2) throws RemoteException;

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcql var2;
      this.zza((var2 = (zzcql)var1).getContext(), (zzcqc)var2.zzrf());
   }
}
