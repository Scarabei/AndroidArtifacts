package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbiq extends zzbjz {
   // $FF: synthetic field
   private FenceUpdateRequest zzaKQ;

   zzbiq(zzbip var1, GoogleApiClient var2, FenceUpdateRequest var3) {
      this.zzaKQ = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbka var3 = (zzbka)var1;
      var3.zza(this, (zzbjj)((zzbjj)this.zzaKQ));
   }
}
