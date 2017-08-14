package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

final class zzcbu extends zzcbw {
   // $FF: synthetic field
   private long zzbiy;
   // $FF: synthetic field
   private PendingIntent zzbiz;

   zzcbu(zzcbt var1, GoogleApiClient var2, long var3, PendingIntent var5) {
      this.zzbiy = var3;
      this.zzbiz = var5;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      var3.zza(this.zzbiy, this.zzbiz);
      this.setResult(Status.zzaBm);
   }
}
