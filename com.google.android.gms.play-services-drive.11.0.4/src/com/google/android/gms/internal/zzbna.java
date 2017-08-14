package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFile;

final class zzbna extends zzbkt {
   private final zzbaz zzaIz;

   public zzbna(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void zza(zzbpf var1) throws RemoteException {
      this.zzaIz.setResult(new zzbnc(Status.zzaBm, new zzbms(var1.zzaNt)));
   }

   public final void onError(Status var1) throws RemoteException {
      this.zzaIz.setResult(new zzbnc(var1, (DriveFile)null));
   }
}
