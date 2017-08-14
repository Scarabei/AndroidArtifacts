package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbar extends zzban {
   private zzbdy zzaBy;

   public zzbar(zzbdy var1, TaskCompletionSource var2) {
      super(4, var2);
      this.zzaBy = var1;
   }

   public final void zzb(zzbdd var1) throws RemoteException {
      zzbef var2;
      if ((var2 = (zzbef)var1.zzqs().remove(this.zzaBy)) != null) {
         var2.zzaBv.zzc(var1.zzpJ(), this.zzalE);
         var2.zzaBu.zzqH();
      } else {
         Log.wtf("UnregisterListenerTask", "Received call to unregister a listener without a matching registration call.", new Exception());
         this.zzalE.trySetException(new ApiException(Status.zzaBo));
      }
   }
}
