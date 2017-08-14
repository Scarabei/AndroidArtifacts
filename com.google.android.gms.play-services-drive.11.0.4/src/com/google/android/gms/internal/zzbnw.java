package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Metadata;

final class zzbnw extends zzbkt {
   private final zzbaz zzaIz;

   public zzbnw(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void zza(zzbpq var1) throws RemoteException {
      this.zzaIz.setResult(new zzbnx(Status.zzaBm, new zzblj(var1.zzaND)));
   }

   public final void onError(Status var1) throws RemoteException {
      this.zzaIz.setResult(new zzbnx(var1, (Metadata)null));
   }
}
