package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.PutDataRequest;

final class zzbj extends zzn {
   // $FF: synthetic field
   private PutDataRequest zzbSw;

   zzbj(zzbi var1, GoogleApiClient var2, PutDataRequest var3) {
      this.zzbSw = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, (PutDataRequest)this.zzbSw);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzbs(var1, (DataItem)null);
   }
}
