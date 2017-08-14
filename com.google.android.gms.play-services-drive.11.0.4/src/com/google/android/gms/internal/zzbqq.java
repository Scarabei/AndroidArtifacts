package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public final class zzbqq extends zzbkt {
   private final zzbaz zzaIz;

   public zzbqq(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void onSuccess() throws RemoteException {
      this.zzaIz.setResult(Status.zzaBm);
   }

   public final void onError(Status var1) throws RemoteException {
      this.zzaIz.setResult(var1);
   }
}
