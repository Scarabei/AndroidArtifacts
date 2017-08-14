package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder;

final class zzbnb extends zzbkt {
   private final zzbaz zzaIz;

   public zzbnb(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void zza(zzbpf var1) throws RemoteException {
      this.zzaIz.setResult(new zzbne(Status.zzaBm, new zzbmx(var1.zzaNt)));
   }

   public final void onError(Status var1) throws RemoteException {
      this.zzaIz.setResult(new zzbne(var1, (DriveFolder)null));
   }
}
