package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzaqc extends zzapz {
   // $FF: synthetic field
   private zzaqb zzajY;

   zzaqc(zzaqb var1) {
      this.zzajY = var1;
      super();
   }

   public final void zzc(Status var1) throws RemoteException {
      this.zzajY.setResult(var1);
   }
}
