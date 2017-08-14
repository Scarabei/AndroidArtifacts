package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeh;
import java.io.IOException;

final class zzf implements zzi {
   // $FF: synthetic field
   private String zzakq;
   // $FF: synthetic field
   private Bundle val$extras;

   zzf(String var1, Bundle var2) {
      this.zzakq = var1;
      this.val$extras = var2;
      super();
   }

   // $FF: synthetic method
   public final Object zzy(IBinder var1) throws RemoteException, IOException, GoogleAuthException {
      Bundle var4;
      String var5 = (var4 = (Bundle)zzd.zzm(zzeh.zza(var1).zza(this.zzakq, this.val$extras))).getString("Error");
      if (!var4.getBoolean("booleanResult")) {
         throw new GoogleAuthException(var5);
      } else {
         return null;
      }
   }
}
