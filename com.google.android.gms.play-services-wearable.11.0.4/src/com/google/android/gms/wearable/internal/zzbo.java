package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Asset;

final class zzbo extends zzn {
   // $FF: synthetic field
   private Asset zzbSy;

   zzbo(zzbi var1, GoogleApiClient var2, Asset var3) {
      this.zzbSy = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, (Asset)this.zzbSy);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzbu(var1, (ParcelFileDescriptor)null);
   }
}
