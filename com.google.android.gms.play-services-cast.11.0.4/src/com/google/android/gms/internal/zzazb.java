package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

class zzazb extends zzbay {
   // $FF: synthetic field
   final zzayw zzayZ;

   public zzazb(zzayw var1, GoogleApiClient var2) {
      super(zzayw.zzc(var1), var2);
      this.zzayZ = var1;
   }

   public void zza(zzazf var1) throws RemoteException {
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzaze(var1);
   }
}
