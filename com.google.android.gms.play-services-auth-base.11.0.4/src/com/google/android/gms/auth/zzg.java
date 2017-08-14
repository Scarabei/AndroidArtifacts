package com.google.android.gms.auth;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzeh;
import java.io.IOException;

final class zzg implements zzi {
   // $FF: synthetic field
   private String zzakr;
   // $FF: synthetic field
   private int zzaks;

   zzg(String var1, int var2) {
      this.zzakr = var1;
      this.zzaks = var2;
      super();
   }

   // $FF: synthetic method
   public final Object zzy(IBinder var1) throws RemoteException, IOException, GoogleAuthException {
      zzeg var4 = zzeh.zza(var1);
      AccountChangeEventsRequest var5 = (new AccountChangeEventsRequest()).setAccountName(this.zzakr).setEventIndex(this.zzaks);
      return ((AccountChangeEventsResponse)zzd.zzm(var4.zza(var5))).getEvents();
   }
}
