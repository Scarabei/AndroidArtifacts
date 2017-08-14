package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzal extends zzn {
   // $FF: synthetic field
   private zzak zzbSk;

   zzal(zzak var1, GoogleApiClient var2) {
      this.zzbSk = var1;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      String var5 = zzak.zza(this.zzbSk);
      ((zzdn)var3.zzrf()).zzc(new zzfd(this), var5);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return var1;
   }
}
