package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import java.util.ArrayList;

final class zzaty extends zzbjx {
   // $FF: synthetic field
   private int zzaon;

   zzaty(GoogleApiClient var1, int var2) {
      this.zzaon = var2;
      super(var1);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbka var3 = (zzbka)var1;
      var3.zza(this, (zzaub)(new zzaub(this.zzaon, (ArrayList)null)));
   }
}
