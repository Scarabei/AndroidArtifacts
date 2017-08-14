package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.RemoteException;
import com.google.android.gms.auth.account.zzd;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

final class zzaqq extends zzbay {
   // $FF: synthetic field
   private String zzakq;

   zzaqq(zzaqn var1, Api var2, GoogleApiClient var3, String var4) {
      this.zzakq = var4;
      super(var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzaqx var3 = (zzaqx)var1;
      ((zzd)var3.zzrf()).zza(new zzaqr(this), (String)this.zzakq);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzaqv(var1, (Account)null);
   }
}
