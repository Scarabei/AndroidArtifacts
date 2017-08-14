package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzarl extends zzarn {
   zzarl(zzarg var1, GoogleApiClient var2) {
      super(var2);
   }

   protected final void zza(Context var1, zzart var2) throws RemoteException {
      var2.zza(new zzarm(this));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return var1;
   }
}
