package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzbmz extends zzbnf {
   // $FF: synthetic field
   private MetadataChangeSet zzaOr;
   // $FF: synthetic field
   private zzbmx zzaOv;

   zzbmz(zzbmx var1, GoogleApiClient var2, MetadataChangeSet var3) {
      this.zzaOv = var1;
      this.zzaOr = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      this.zzaOr.zzsW().setContext(var3.getContext());
      ((zzbom)var3.zzrf()).zza((zzblh)(new zzblh(this.zzaOv.getDriveId(), this.zzaOr.zzsW())), new zzbnb(this));
   }
}
