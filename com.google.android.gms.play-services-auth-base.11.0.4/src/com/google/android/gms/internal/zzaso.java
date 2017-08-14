package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzaso extends zzasm {
   // $FF: synthetic field
   private ProxyRequest zzalK;

   zzaso(zzasn var1, GoogleApiClient var2, ProxyRequest var3) {
      this.zzalK = var3;
      super(var2);
   }

   protected final void zza(Context var1, zzasb var2) throws RemoteException {
      zzasp var3 = new zzasp(this);
      var2.zza(var3, this.zzalK);
   }
}
