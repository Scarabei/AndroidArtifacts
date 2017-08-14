package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzclp extends zzcmj {
   // $FF: synthetic field
   private String zzbxb;

   zzclp(zzclm var1, GoogleApiClient var2, String var3) {
      this.zzbxb = var3;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      String var4 = this.zzbxb;
      ((zzcnd)var3.zzrf()).zza(new zzcmk(var4));
   }
}
