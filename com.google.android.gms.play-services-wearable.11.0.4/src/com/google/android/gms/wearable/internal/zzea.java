package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Node;

final class zzea extends zzn {
   zzea(zzdz var1, GoogleApiClient var2) {
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      ((zzdn)var3.zzrf()).zzb(new zzfo(this));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzef(var1, (Node)null);
   }
}
