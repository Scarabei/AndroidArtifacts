package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;

final class zzbr extends zzn {
   // $FF: synthetic field
   private DataApi.DataListener zzbSA;

   zzbr(zzbi var1, GoogleApiClient var2, DataApi.DataListener var3) {
      this.zzbSA = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, (DataApi.DataListener)this.zzbSA);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return var1;
   }
}
