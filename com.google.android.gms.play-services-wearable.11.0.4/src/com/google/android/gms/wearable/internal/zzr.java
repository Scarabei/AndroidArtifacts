package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzr extends zzn {
   // $FF: synthetic field
   private String zzbRV;

   zzr(zzo var1, GoogleApiClient var2, String var3) {
      this.zzbRV = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      String var5 = this.zzbRV;
      ((zzdn)var3.zzrf()).zza(new zzfb(this), (String)var5);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzu(var1);
   }
}
