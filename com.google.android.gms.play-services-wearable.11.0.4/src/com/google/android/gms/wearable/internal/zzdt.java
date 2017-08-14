package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzdt extends zzn {
   // $FF: synthetic field
   private String zzbSe;
   // $FF: synthetic field
   private String zzbST;
   // $FF: synthetic field
   private byte[] zzbKQ;

   zzdt(zzds var1, GoogleApiClient var2, String var3, String var4, byte[] var5) {
      this.zzbSe = var3;
      this.zzbST = var4;
      this.zzbKQ = var5;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      byte[] var7 = this.zzbKQ;
      String var6 = this.zzbST;
      String var5 = this.zzbSe;
      ((zzdn)var3.zzrf()).zza(new zzfu(this), var5, var6, var7);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzdw(var1, -1);
   }
}
