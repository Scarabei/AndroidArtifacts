package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.drive.DriveFile;

final class zzbmo extends zzblx {
   // $FF: synthetic field
   private zzbmn zzaOj;

   zzbmo(zzbmn var1, GoogleApiClient var2) {
      this.zzaOj = var1;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbqb)(new zzbqb(this.zzaOj.getDriveId(), 536870912, zzbmn.zza(this.zzaOj).getRequestId())), new zzbqd(this, (DriveFile.DownloadProgressListener)null));
   }
}
