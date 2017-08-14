package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzccl extends zzccm {
   // $FF: synthetic field
   private PendingIntent zzbiz;

   zzccl(zzccb var1, GoogleApiClient var2, PendingIntent var3) {
      this.zzbiz = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      zzccn var4 = new zzccn(this);
      var3.zza((PendingIntent)this.zzbiz, (zzccu)var4);
   }
}
