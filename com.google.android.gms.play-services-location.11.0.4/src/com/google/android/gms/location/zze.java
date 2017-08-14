package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.internal.zzbeq;
import com.google.android.gms.internal.zzcdj;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zze extends zzbeq {
   zze(FusedLocationProviderClient var1) {
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1, TaskCompletionSource var2) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      var2.setResult(var3.getLastLocation());
   }
}
