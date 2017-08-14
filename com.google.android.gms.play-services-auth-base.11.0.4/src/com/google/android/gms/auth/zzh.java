package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeh;
import java.io.IOException;

final class zzh implements zzi {
   // $FF: synthetic field
   private Account zzako;

   zzh(Account var1) {
      this.zzako = var1;
      super();
   }

   // $FF: synthetic method
   public final Object zzy(IBinder var1) throws RemoteException, IOException, GoogleAuthException {
      return (Bundle)zzd.zzm(zzeh.zza(var1).zza(this.zzako));
   }
}
