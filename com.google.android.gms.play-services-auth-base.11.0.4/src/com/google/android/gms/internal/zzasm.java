package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.zzd;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

abstract class zzasm extends zzbay {
   public zzasm(GoogleApiClient var1) {
      super(zzd.API, var1);
   }

   protected abstract void zza(Context var1, zzasb var2) throws RemoteException;

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzary var2;
      this.zza((var2 = (zzary)var1).getContext(), (zzasb)var2.zzrf());
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzasq(var1);
   }
}
