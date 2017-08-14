package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.plus.internal.zzh;

final class zzcrc extends zzcrg {
   // $FF: synthetic field
   private String zzbAP;

   zzcrc(zzcra var1, GoogleApiClient var2, String var3) {
      this.zzbAP = var3;
      super(var2, (zzcrb)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzh var3 = (zzh)var1;
      String var5 = this.zzbAP;
      this.zza(var3.zza(this, 0, var5));
   }
}
