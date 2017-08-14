package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveId;

final class zzbly extends zzbkt {
   private final zzbaz zzaIz;

   public zzbly(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void zza(zzbpq var1) throws RemoteException {
      this.zzaIz.setResult(new zzblz(Status.zzaBm, (new zzblj(var1.zzaND)).getDriveId()));
   }

   public final void zza(zzbpf var1) throws RemoteException {
      this.zzaIz.setResult(new zzblz(Status.zzaBm, var1.zzaNt));
   }

   public final void onError(Status var1) throws RemoteException {
      this.zzaIz.setResult(new zzblz(var1, (DriveId)null));
   }
}
