package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.awareness.fence.FenceQueryRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbir extends zzbjv {
   // $FF: synthetic field
   private FenceQueryRequest zzaKR;

   zzbir(zzbip var1, GoogleApiClient var2, FenceQueryRequest var3) {
      this.zzaKR = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbka var3 = (zzbka)var1;
      var3.zza(this, (zzbja)((zzbja)this.zzaKR));
   }
}
