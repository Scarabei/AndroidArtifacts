package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.request.zzbi;

final class zzbyo extends zzbvj {
   // $FF: synthetic field
   private Subscription zzaVS;

   zzbyo(zzbyl var1, GoogleApiClient var2, Subscription var3) {
      this.zzaVS = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvg var3 = (zzbvg)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwr)var3.zzrf()).zza(new zzbi(this.zzaVS, false, var4));
   }
}
