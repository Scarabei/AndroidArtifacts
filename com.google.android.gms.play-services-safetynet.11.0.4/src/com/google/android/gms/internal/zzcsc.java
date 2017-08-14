package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import java.util.List;

final class zzcsc extends zzcsa.zzf {
   // $FF: synthetic field
   private List zzbBQ;
   // $FF: synthetic field
   private String zzbBR;
   // $FF: synthetic field
   private String zzbBP;

   zzcsc(zzcsa var1, GoogleApiClient var2, List var3, String var4, String var5) {
      this.zzbBQ = var3;
      this.zzbBR = var4;
      this.zzbBP = var5;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcsn var3 = (zzcsn)var1;
      var3.zza(this.zzbBW, this.zzbBQ, 2, this.zzbBR, this.zzbBP);
   }
}
