package com.google.android.gms.safetynet;

import android.os.RemoteException;
import com.google.android.gms.internal.zzbeq;
import com.google.android.gms.internal.zzcry;
import com.google.android.gms.internal.zzcsn;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzj extends zzbeq {
   zzj(SafetyNetClient var1) {
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1, TaskCompletionSource var2) throws RemoteException {
      zzcsn var4 = (zzcsn)var1;
      zzk var6 = new zzk(this, var2);
      ((zzcry)var4.zzrf()).zza(var6);
   }
}
