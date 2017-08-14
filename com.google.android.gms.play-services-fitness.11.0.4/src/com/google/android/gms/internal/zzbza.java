package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.zzay;

final class zzbza extends zzbvr {
   // $FF: synthetic field
   private Session zzaWc;

   zzbza(zzbyz var1, GoogleApiClient var2, Session var3) {
      this.zzaWc = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvo var3 = (zzbvo)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwv)var3.zzrf()).zza(new zzay(this.zzaWc, var4));
   }
}
