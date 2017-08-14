package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzam extends zzn {
   // $FF: synthetic field
   private int zzKc;
   // $FF: synthetic field
   private zzak zzbSk;

   zzam(zzak var1, GoogleApiClient var2, int var3) {
      this.zzbSk = var1;
      this.zzKc = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      String var10002 = zzak.zza(this.zzbSk);
      int var6 = this.zzKc;
      String var5 = var10002;
      ((zzdn)var3.zzrf()).zzb(new zzfe(this), (String)var5, var6);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return var1;
   }
}
