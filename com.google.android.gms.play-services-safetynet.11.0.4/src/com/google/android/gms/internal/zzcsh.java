package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzcsh extends zzcsa.zze {
   // $FF: synthetic field
   private String zzbBU;

   zzcsh(zzcsa var1, GoogleApiClient var2, String var3) {
      this.zzbBU = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcsn var3 = (zzcsn)var1;
      String var5 = this.zzbBU;
      zzcrw var4 = this.zzbBW;
      ((zzcry)var3.zzrf()).zza(var4, var5);
   }
}
