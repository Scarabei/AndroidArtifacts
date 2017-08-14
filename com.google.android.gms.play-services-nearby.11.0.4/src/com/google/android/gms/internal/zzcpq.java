package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.messages.internal.zzq;

public final class zzcpq extends zzq implements zzcpn {
   private final zzbdw zzbzE;
   private boolean zzbzG = false;

   public zzcpq(zzbdw var1) {
      this.zzbzE = var1;
   }

   public final synchronized void zzG(Status var1) throws RemoteException {
      if (!this.zzbzG) {
         this.zzbzE.zza(new zzcpr(this, var1));
         this.zzbzG = true;
      } else {
         String var2 = String.valueOf(var1);
         Log.wtf("NearbyMessagesCallbackWrapper", (new StringBuilder(28 + String.valueOf(var2).length())).append("Received multiple statuses: ").append(var2).toString(), new Exception());
      }
   }

   public final zzbdw zzzX() {
      return this.zzbzE;
   }
}
