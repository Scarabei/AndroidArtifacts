package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Arrays;
import java.util.List;

final class zzk extends com.google.android.gms.location.places.zzm.zzc {
   // $FF: synthetic field
   private String[] zzbkl;

   zzk(zzh var1, Api var2, GoogleApiClient var3, String[] var4) {
      this.zzbkl = var4;
      super(var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzm var3 = (zzm)var1;
      List var4 = Arrays.asList(this.zzbkl);
      var3.zza(new com.google.android.gms.location.places.zzm(this), var4);
   }
}
