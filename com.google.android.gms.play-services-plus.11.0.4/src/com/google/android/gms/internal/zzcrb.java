package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.plus.internal.zzh;

final class zzcrb extends zzcrg {
   // $FF: synthetic field
   private int zzbAO;
   // $FF: synthetic field
   private String zzbAP;

   zzcrb(zzcra var1, GoogleApiClient var2, int var3, String var4) {
      this.zzbAO = var3;
      this.zzbAP = var4;
      super(var2, (zzcrb)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzh var3 = (zzh)var1;
      this.zza(var3.zza(this, this.zzbAO, this.zzbAP));
   }
}
