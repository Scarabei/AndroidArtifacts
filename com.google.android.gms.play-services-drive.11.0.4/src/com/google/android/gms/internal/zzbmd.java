package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.MetadataBuffer;

final class zzbmd extends zzbkt {
   private final zzbaz zzaIz;

   public zzbmd(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void zza(zzbpl var1) throws RemoteException {
      MetadataBuffer var2 = new MetadataBuffer(var1.zzaPi);
      this.zzaIz.setResult(new zzbmb(Status.zzaBm, var2, var1.zzaNP));
   }

   public final void onError(Status var1) throws RemoteException {
      this.zzaIz.setResult(new zzbmb(var1, (MetadataBuffer)null, false));
   }
}
