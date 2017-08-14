package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.internal.zzbdy;
import com.google.android.gms.internal.zzbey;
import com.google.android.gms.internal.zzccu;
import com.google.android.gms.internal.zzcdj;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzh extends zzbey {
   zzh(FusedLocationProviderClient var1, zzbdy var2) {
      super(var2);
   }

   // $FF: synthetic method
   protected final void zzc(com.google.android.gms.common.api.Api.zzb var1, TaskCompletionSource var2) throws RemoteException {
      zzcdj var4 = (zzcdj)var1;
      var4.zzb(this.zzqG(), (zzccu)null);
   }
}
