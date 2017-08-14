package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzaq extends com.google.android.gms.location.places.zze {
   // $FF: synthetic field
   private int zzbla;
   // $FF: synthetic field
   private int zzblb;
   // $FF: synthetic field
   private zzap zzblc;

   zzaq(zzap var1, Api var2, GoogleApiClient var3, int var4, int var5) {
      this.zzblc = var1;
      this.zzbla = var4;
      this.zzblb = var5;
      super(var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzm var3 = (zzm)var1;
      var3.zza(new com.google.android.gms.location.places.zzd(this), zzap.zza(this.zzblc), this.zzbla, this.zzblb, zzap.zzb(this.zzblc));
   }
}
