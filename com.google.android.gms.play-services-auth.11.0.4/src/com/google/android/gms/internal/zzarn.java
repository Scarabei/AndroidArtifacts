package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

abstract class zzarn extends zzbay {
   zzarn(GoogleApiClient var1) {
      super(Auth.CREDENTIALS_API, var1);
   }

   protected abstract void zza(Context var1, zzart var2) throws DeadObjectException, RemoteException;

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzaro var2;
      this.zza((var2 = (zzaro)var1).getContext(), (zzart)var2.zzrf());
   }
}
