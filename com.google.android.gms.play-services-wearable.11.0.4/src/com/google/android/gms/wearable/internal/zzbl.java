package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemBuffer;

final class zzbl extends zzn {
   zzbl(zzbi var1, GoogleApiClient var2) {
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      ((zzdn)var3.zzrf()).zza(new zzfm(this));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new DataItemBuffer(DataHolder.zzau(var1.getStatusCode()));
   }
}
