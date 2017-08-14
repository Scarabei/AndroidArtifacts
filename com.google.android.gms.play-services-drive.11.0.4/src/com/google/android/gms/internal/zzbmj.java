package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbmj extends zzbmg {
   // $FF: synthetic field
   private zzbqk zzaOc;
   // $FF: synthetic field
   private zzboc zzaOb;

   zzbmj(zzbmh var1, GoogleApiClient var2, zzbqk var3, zzboc var4) {
      this.zzaOc = var3;
      this.zzaOb = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbqk)this.zzaOc, this.zzaOb, (String)null, new zzbqq(this));
   }
}
