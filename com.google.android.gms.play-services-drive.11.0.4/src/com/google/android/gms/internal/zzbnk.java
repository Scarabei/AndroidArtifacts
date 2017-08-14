package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.FileUploadPreferences;

final class zzbnk extends zzbkt {
   private final zzbaz zzaIz;
   // $FF: synthetic field
   private zzbnh zzaOz;

   private zzbnk(zzbnh var1, zzbaz var2) {
      this.zzaOz = var1;
      super();
      this.zzaIz = var2;
   }

   public final void zza(zzbpb var1) throws RemoteException {
      this.zzaIz.setResult(new zzbnl(this.zzaOz, Status.zzaBm, var1.zzaOW, (zzbni)null));
   }

   public final void onError(Status var1) throws RemoteException {
      this.zzaIz.setResult(new zzbnl(this.zzaOz, var1, (FileUploadPreferences)null, (zzbni)null));
   }

   // $FF: synthetic method
   zzbnk(zzbnh var1, zzbaz var2, zzbni var3) {
      this(var1, var2);
   }
}
