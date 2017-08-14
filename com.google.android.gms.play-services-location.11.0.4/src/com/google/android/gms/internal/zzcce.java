package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

final class zzcce extends zzccm {
   // $FF: synthetic field
   private boolean zzbiG;

   zzcce(zzccb var1, GoogleApiClient var2, boolean var3) {
      this.zzbiG = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      var3.zzai(this.zzbiG);
      this.setResult(Status.zzaBm);
   }
}
