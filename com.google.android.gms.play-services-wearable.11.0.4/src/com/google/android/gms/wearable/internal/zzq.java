package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Map;

final class zzq extends zzn {
   // $FF: synthetic field
   private int zzbRW;

   zzq(zzo var1, GoogleApiClient var2, int var3) {
      this.zzbRW = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      int var5 = this.zzbRW;
      ((zzdn)var3.zzrf()).zza(new zzfg(this), var5);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzx(var1, (Map)null);
   }
}
