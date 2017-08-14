package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzbn extends zzn {
   // $FF: synthetic field
   private Uri zzbzR;
   // $FF: synthetic field
   private int zzbSx;

   zzbn(zzbi var1, GoogleApiClient var2, Uri var3, int var4) {
      this.zzbzR = var3;
      this.zzbSx = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      int var6 = this.zzbSx;
      Uri var5 = this.zzbzR;
      ((zzdn)var3.zzrf()).zzb(new zzff(this), (Uri)var5, var6);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzbt(var1, 0);
   }
}
