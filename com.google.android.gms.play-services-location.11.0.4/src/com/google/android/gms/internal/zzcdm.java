package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.LocationSettingsResult;

final class zzcdm extends zzcdc {
   private zzbaz zzbiT;

   public zzcdm(zzbaz var1) {
      zzbo.zzb(var1 != null, "listener can't be null.");
      this.zzbiT = var1;
   }

   public final void zza(LocationSettingsResult var1) throws RemoteException {
      this.zzbiT.setResult(var1);
      this.zzbiT = null;
   }
}
