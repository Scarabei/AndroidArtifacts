package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzayy extends zzazb {
   // $FF: synthetic field
   private String zztC;
   // $FF: synthetic field
   private zzayw zzayZ;

   zzayy(zzayw var1, GoogleApiClient var2, String var3) {
      this.zzayZ = var1;
      this.zztC = var3;
      super(var1, var2);
   }

   public final void zza(zzazf var1) throws RemoteException {
      var1.zza(new zzazc(this, var1), zzayw.zzb(this.zzayZ), this.zztC);
   }
}
