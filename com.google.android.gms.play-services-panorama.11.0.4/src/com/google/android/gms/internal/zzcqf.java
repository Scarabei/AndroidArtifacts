package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcqf extends zzcqi {
   // $FF: synthetic field
   private Uri zzbzR;

   zzcqf(zzcqe var1, GoogleApiClient var2, Uri var3) {
      this.zzbzR = var3;
      super(var2);
   }

   protected final void zza(Context var1, zzcqc var2) throws RemoteException {
      zzcqj var3 = new zzcqj(this);
      var2.zza(var3, this.zzbzR, (Bundle)null, false);
   }
}
