package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.drive.DriveId;

final class zzblr extends zzbma {
   // $FF: synthetic field
   private String zzaNM;

   zzblr(zzblo var1, GoogleApiClient var2, String var3) {
      this.zzaNM = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzboi)(new zzboi(DriveId.zzcO(this.zzaNM), false)), new zzbly(this));
   }
}
