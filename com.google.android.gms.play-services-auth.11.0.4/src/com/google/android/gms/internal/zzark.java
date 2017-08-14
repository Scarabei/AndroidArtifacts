package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzark extends zzarn {
   // $FF: synthetic field
   private Credential zzalA;

   zzark(zzarg var1, GoogleApiClient var2, Credential var3) {
      this.zzalA = var3;
      super(var2);
   }

   protected final void zza(Context var1, zzart var2) throws RemoteException {
      var2.zza(new zzarm(this), (zzarp)(new zzarp(this.zzalA)));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return var1;
   }
}
