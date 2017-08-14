package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.internal.zzap;
import com.google.android.gms.drive.DriveFile;

final class zzbmt extends zzblx {
   // $FF: synthetic field
   private int zzaNL;
   // $FF: synthetic field
   private DriveFile.DownloadProgressListener zzaOm;
   // $FF: synthetic field
   private zzbms zzaOn;

   zzbmt(zzbms var1, GoogleApiClient var2, int var3, DriveFile.DownloadProgressListener var4) {
      this.zzaOn = var1;
      this.zzaNL = var3;
      this.zzaOm = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      zzboa var4 = ((zzbom)var3.zzrf()).zza((zzbqb)(new zzbqb(this.zzaOn.getDriveId(), this.zzaNL, 0)), new zzbqd(this, this.zzaOm));
      this.zza(zzap.zzH(var4.zzaOG));
   }
}
