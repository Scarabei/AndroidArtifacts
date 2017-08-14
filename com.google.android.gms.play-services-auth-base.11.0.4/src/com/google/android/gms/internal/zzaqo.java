package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zzd;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

final class zzaqo extends zzbay {
   // $FF: synthetic field
   private boolean val$enabled;

   zzaqo(zzaqn var1, Api var2, GoogleApiClient var3, boolean var4) {
      this.val$enabled = var4;
      super(var2, var3);
   }

   protected final Result zzb(Status var1) {
      return new zzaqp(this, var1);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzaqx var3 = (zzaqx)var1;
      ((zzd)var3.zzrf()).zzO(this.val$enabled);
   }
}
