package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzasl extends zzbeq {
   private TaskCompletionSource zzalE;

   private zzasl() {
   }

   protected final void zzg(Status var1) {
      TaskCompletionSource var2 = this.zzalE;
      zzber.zza(var1, (Object)null, var2);
   }

   protected abstract void zza(zzasd var1) throws RemoteException;

   // $FF: synthetic method
   protected final void zza(zzb var1, TaskCompletionSource var2) throws RemoteException {
      zzash var4 = (zzash)var1;
      this.zzalE = var2;
      this.zza((zzasd)var4.zzrf());
   }

   // $FF: synthetic method
   zzasl(zzasj var1) {
      this();
   }
}
