package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzbnr extends zzbny {
   // $FF: synthetic field
   private MetadataChangeSet zzaOr;
   // $FF: synthetic field
   private zzbnn zzaOD;

   zzbnr(zzbnn var1, GoogleApiClient var2, MetadataChangeSet var3) {
      this.zzaOD = var1;
      this.zzaOr = var3;
      super(var1, var2, (zzbno)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      this.zzaOr.zzsW().setContext(var3.getContext());
      ((zzbom)var3.zzrf()).zza((zzbqx)(new zzbqx(this.zzaOD.zzaLV, this.zzaOr.zzsW())), new zzbnw(this));
   }
}
