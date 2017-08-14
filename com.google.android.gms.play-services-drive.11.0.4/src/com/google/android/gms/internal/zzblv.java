package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveContents;

final class zzblv extends zzbkt {
   private final zzbaz zzaIz;

   public zzblv(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void zza(zzboz var1) throws RemoteException {
      this.zzaIz.setResult(new zzblw(Status.zzaBm, new zzbmn(var1.zzaOg)));
   }

   public final void onError(Status var1) throws RemoteException {
      this.zzaIz.setResult(new zzblw(var1, (DriveContents)null));
   }
}
