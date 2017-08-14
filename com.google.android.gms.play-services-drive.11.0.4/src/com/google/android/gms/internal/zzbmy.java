package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzm;

final class zzbmy extends zzbnd {
   // $FF: synthetic field
   private MetadataChangeSet zzaOr;
   // $FF: synthetic field
   private int zzaOs;
   // $FF: synthetic field
   private int zzaOt;
   // $FF: synthetic field
   private zzm zzaOu;
   // $FF: synthetic field
   private zzbmx zzaOv;

   zzbmy(zzbmx var1, GoogleApiClient var2, MetadataChangeSet var3, int var4, int var5, zzm var6) {
      this.zzaOv = var1;
      this.zzaOr = var3;
      this.zzaOs = var4;
      this.zzaOt = var5;
      this.zzaOu = var6;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      this.zzaOr.zzsW().setContext(var3.getContext());
      zzblf var4 = new zzblf(this.zzaOv.getDriveId(), this.zzaOr.zzsW(), this.zzaOs, this.zzaOt, this.zzaOu);
      ((zzbom)var3.zzrf()).zza((zzblf)var4, new zzbna(this));
   }
}
