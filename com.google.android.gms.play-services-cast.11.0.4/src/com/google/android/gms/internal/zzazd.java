package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public final class zzazd extends zzaza {
   // $FF: synthetic field
   private zzazb zzazb;

   protected zzazd(zzazb var1) {
      this.zzazb = var1;
      super(var1.zzayZ);
   }

   public final void onDisconnected() throws RemoteException {
      zzayw.zzoQ().zzb("onDisconnected");
      zzayw.zza(this.zzazb.zzayZ);
      this.zzazb.setResult(new zzaze(Status.zzaBm));
   }

   public final void onError(int var1) throws RemoteException {
      zzayw.zzoQ().zzb("onError: %d", var1);
      zzayw.zza(this.zzazb.zzayZ);
      this.zzazb.setResult(new zzaze(Status.zzaBo));
   }
}
