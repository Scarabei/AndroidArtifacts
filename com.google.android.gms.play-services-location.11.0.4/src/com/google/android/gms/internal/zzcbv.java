package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

final class zzcbv extends zzcbw {
   // $FF: synthetic field
   private PendingIntent zzbiz;

   zzcbv(zzcbt var1, GoogleApiClient var2, PendingIntent var3) {
      this.zzbiz = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      var3.zzc(this.zzbiz);
      this.setResult(Status.zzaBm);
   }
}
