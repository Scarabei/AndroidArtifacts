package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityInfo;

final class zzp extends zzn {
   // $FF: synthetic field
   private String zzbRV;
   // $FF: synthetic field
   private int zzbRW;

   zzp(zzo var1, GoogleApiClient var2, String var3, int var4) {
      this.zzbRV = var3;
      this.zzbRW = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      int var6 = this.zzbRW;
      String var5 = this.zzbRV;
      ((zzdn)var3.zzrf()).zza(new zzfh(this), (String)var5, var6);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzy(var1, (CapabilityInfo)null);
   }
}
