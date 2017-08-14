package com.google.android.gms.identity.intents;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcbe;

final class zzb extends Address.zza {
   // $FF: synthetic field
   private UserAddressRequest zzbgA;
   // $FF: synthetic field
   private int val$requestCode;

   zzb(GoogleApiClient var1, UserAddressRequest var2, int var3) {
      this.zzbgA = var2;
      this.val$requestCode = var3;
      super(var1);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzcbe var3 = (zzcbe)var1;
      var3.zza(this.zzbgA, this.val$requestCode);
      this.setResult(Status.zzaBm);
   }
}
