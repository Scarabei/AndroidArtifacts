package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbap extends zzban {
   private zzbee zzaBu;
   private zzbey zzaBv;

   public zzbap(zzbef var1, TaskCompletionSource var2) {
      super(3, var2);
      this.zzaBu = var1.zzaBu;
      this.zzaBv = var1.zzaBv;
   }

   public final void zzb(zzbdd var1) throws RemoteException {
      this.zzaBu.zzb(var1.zzpJ(), this.zzalE);
      if (this.zzaBu.zzqG() != null) {
         var1.zzqs().put(this.zzaBu.zzqG(), new zzbef(this.zzaBu, this.zzaBv));
      }

   }
}
