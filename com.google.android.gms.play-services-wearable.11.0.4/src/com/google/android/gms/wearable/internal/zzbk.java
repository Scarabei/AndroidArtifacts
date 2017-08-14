package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataItem;

final class zzbk extends zzn {
   // $FF: synthetic field
   private Uri zzbzR;

   zzbk(zzbi var1, GoogleApiClient var2, Uri var3) {
      this.zzbzR = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      Uri var5 = this.zzbzR;
      ((zzdn)var3.zzrf()).zza(new zzfl(this), (Uri)var5);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzbs(var1, (DataItem)null);
   }
}
