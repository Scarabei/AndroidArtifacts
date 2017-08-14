package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.nearby.connection.Payload;

final class zzcmf extends zzcmj {
   // $FF: synthetic field
   private String zzbxb;
   // $FF: synthetic field
   private Payload zzbxa;

   zzcmf(zzclm var1, GoogleApiClient var2, String var3, Payload var4) {
      this.zzbxb = var3;
      this.zzbxa = var4;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      var3.zza(this, new String[]{this.zzbxb}, this.zzbxa, false);
   }
}
