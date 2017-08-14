package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzaqe extends zzapz {
   // $FF: synthetic field
   private zzaqd zzajZ;

   zzaqe(zzaqd var1) {
      this.zzajZ = var1;
      super();
   }

   public final void zzc(Status var1) throws RemoteException {
      this.zzajZ.setResult(var1);
   }
}
