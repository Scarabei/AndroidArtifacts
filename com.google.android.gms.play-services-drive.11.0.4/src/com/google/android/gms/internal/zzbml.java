package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.drive.DriveId;

final class zzbml extends zzbmg {
   // $FF: synthetic field
   private DriveId zzaOd;
   // $FF: synthetic field
   private int zzaOe;

   zzbml(zzbmh var1, GoogleApiClient var2, DriveId var3, int var4) {
      this.zzaOd = var3;
      this.zzaOe = 1;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbqk)(new zzbqk(this.zzaOd, this.zzaOe)), (zzboq)null, (String)null, new zzbqq(this));
   }
}
