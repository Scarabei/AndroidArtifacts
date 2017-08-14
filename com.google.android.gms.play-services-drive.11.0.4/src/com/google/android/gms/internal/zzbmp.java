package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzp;

final class zzbmp extends zzbmg {
   // $FF: synthetic field
   private MetadataChangeSet zzaOk;
   // $FF: synthetic field
   private zzp zzaOl;
   // $FF: synthetic field
   private zzbmn zzaOj;

   zzbmp(zzbmn var1, GoogleApiClient var2, MetadataChangeSet var3, zzp var4) {
      this.zzaOj = var1;
      this.zzaOk = var3;
      this.zzaOl = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      this.zzaOk.zzsW().setContext(var3.getContext());
      ((zzbom)var3.zzrf()).zza((zzbkw)(new zzbkw(zzbmn.zza(this.zzaOj).getDriveId(), this.zzaOk.zzsW(), zzbmn.zza(this.zzaOj).getRequestId(), zzbmn.zza(this.zzaOj).zzsK(), this.zzaOl)), new zzbqq(this));
   }
}
