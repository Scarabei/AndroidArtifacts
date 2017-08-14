package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzbft extends zzbfn {
   private final zzbaz zzaIz;

   public zzbft(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void zzaC(int var1) throws RemoteException {
      this.zzaIz.setResult(new Status(var1));
   }
}
