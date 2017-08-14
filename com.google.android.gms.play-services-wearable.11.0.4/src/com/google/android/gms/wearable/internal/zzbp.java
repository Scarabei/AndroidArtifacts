package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataItemAsset;

final class zzbp extends zzn {
   // $FF: synthetic field
   private DataItemAsset zzbSz;

   zzbp(zzbi var1, GoogleApiClient var2, DataItemAsset var3) {
      this.zzbSz = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      DataItemAsset var5 = this.zzbSz;
      var3.zza(this, (Asset)Asset.createFromRef(var5.getId()));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzbu(var1, (ParcelFileDescriptor)null);
   }
}
