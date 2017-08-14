package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzk extends zza {
   // $FF: synthetic field
   private zzj zzamq;

   zzk(zzj var1) {
      this.zzamq = var1;
      super();
   }

   public final void zzi(Status var1) throws RemoteException {
      this.zzamq.setResult(var1);
   }
}
