package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Channel;

final class zzad extends zzn {
   // $FF: synthetic field
   private String zzbSe;
   // $FF: synthetic field
   private String zzKS;

   zzad(zzac var1, GoogleApiClient var2, String var3, String var4) {
      this.zzbSe = var3;
      this.zzKS = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      String var6 = this.zzKS;
      String var5 = this.zzbSe;
      ((zzdn)var3.zzrf()).zza(new zzfq(this), (String)var5, (String)var6);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzaf(var1, (Channel)null);
   }
}
