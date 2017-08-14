package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;

final class zzbqd extends zzbkt {
   private final zzbaz zzaIz;
   private final DriveFile.DownloadProgressListener zzaPp;

   zzbqd(zzbaz var1, DriveFile.DownloadProgressListener var2) {
      this.zzaIz = var1;
      this.zzaPp = var2;
   }

   public final void zza(zzboz var1) throws RemoteException {
      Status var2 = var1.zzaOV ? new Status(-1) : Status.zzaBm;
      this.zzaIz.setResult(new zzblw(var2, new zzbmn(var1.zzaOg)));
   }

   public final void zza(zzbpd var1) throws RemoteException {
      if (this.zzaPp != null) {
         this.zzaPp.onProgress(var1.zzaOY, var1.zzaOZ);
      }

   }

   public final void onError(Status var1) throws RemoteException {
      this.zzaIz.setResult(new zzblw(var1, (DriveContents)null));
   }
}
