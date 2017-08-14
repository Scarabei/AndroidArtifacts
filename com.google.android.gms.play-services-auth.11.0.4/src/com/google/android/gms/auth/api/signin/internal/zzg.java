package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;

final class zzg extends zza {
   // $FF: synthetic field
   private zzf zzamo;

   zzg(zzf var1) {
      this.zzamo = var1;
      super();
   }

   public final void zza(GoogleSignInAccount var1, Status var2) throws RemoteException {
      if (var1 != null) {
         this.zzamo.zzamm.zza(var1, this.zzamo.zzamn);
      }

      this.zzamo.setResult(new GoogleSignInResult(var1, var2));
   }
}
