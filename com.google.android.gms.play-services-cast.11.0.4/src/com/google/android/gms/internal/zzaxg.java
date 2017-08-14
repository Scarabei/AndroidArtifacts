package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;

public abstract class zzaxg extends zzaxs {
   protected zzayt zzarw;

   public zzaxg(zzawy var1) {
      super(zzawy.zza(var1));
   }

   public abstract void execute();

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      this.execute();
   }
}
