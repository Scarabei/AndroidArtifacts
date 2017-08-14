package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.plus.internal.zzh;
import java.util.Collection;

final class zzcre extends zzcrg {
   // $FF: synthetic field
   private Collection zzbAQ;

   zzcre(zzcra var1, GoogleApiClient var2, Collection var3) {
      this.zzbAQ = var3;
      super(var2, (zzcrb)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzh var3 = (zzh)var1;
      var3.zza(this, this.zzbAQ);
   }
}
