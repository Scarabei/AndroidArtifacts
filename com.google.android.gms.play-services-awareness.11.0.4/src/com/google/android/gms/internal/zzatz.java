package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import java.util.ArrayList;

final class zzatz extends zzbjx {
   // $FF: synthetic field
   private int zzaon;
   // $FF: synthetic field
   private ArrayList zzaoo;

   zzatz(GoogleApiClient var1, int var2, ArrayList var3) {
      this.zzaon = 10003;
      this.zzaoo = var3;
      super(var1);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbka var3 = (zzbka)var1;
      var3.zza(this, (zzaub)(new zzaub(this.zzaon, this.zzaoo)));
   }
}
