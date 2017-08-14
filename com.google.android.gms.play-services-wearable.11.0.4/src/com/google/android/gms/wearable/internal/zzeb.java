package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;

final class zzeb extends zzn {
   zzeb(zzdz var1, GoogleApiClient var2) {
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      ((zzdn)var3.zzrf()).zzc(new zzfk(this));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzee(var1, new ArrayList());
   }
}
