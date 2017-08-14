package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzcsb extends zzcsa.zzb {
   // $FF: synthetic field
   private byte[] zzbBO;
   // $FF: synthetic field
   private String zzbBP;

   zzcsb(GoogleApiClient var1, byte[] var2, String var3) {
      this.zzbBO = var2;
      this.zzbBP = var3;
      super(var1);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcsn var3 = (zzcsn)var1;
      var3.zzb(this.zzbBW, this.zzbBO, this.zzbBP);
   }
}
