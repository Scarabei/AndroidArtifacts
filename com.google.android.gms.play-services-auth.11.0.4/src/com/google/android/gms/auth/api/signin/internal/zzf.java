package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzf extends zzl {
   // $FF: synthetic field
   final zzy zzamm;
   // $FF: synthetic field
   final GoogleSignInOptions zzamn;

   zzf(GoogleApiClient var1, zzy var2, GoogleSignInOptions var3) {
      super(var1);
      this.zzamm = var2;
      this.zzamn = var3;
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzd var3 = (zzd)var1;
      ((zzt)var3.zzrf()).zza(new zzg(this), this.zzamn);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new GoogleSignInResult((GoogleSignInAccount)null, var1);
   }
}
