package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.io.OutputStream;

final class zzao extends zzn {
   // $FF: synthetic field
   private zzak zzbSk;

   zzao(zzak var1, GoogleApiClient var2) {
      this.zzbSk = var1;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      String var6 = zzak.zza(this.zzbSk);
      zzbd var7 = new zzbd();
      ((zzdn)var3.zzrf()).zzb(new zzfj(this, var7), var7, var6);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzat(var1, (OutputStream)null);
   }
}
