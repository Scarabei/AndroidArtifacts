package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzbee {
   private final zzbdw zzaEU;

   protected zzbee(zzbdw var1) {
      this.zzaEU = var1;
   }

   protected abstract void zzb(Api.zzb var1, TaskCompletionSource var2) throws RemoteException;

   public final zzbdy zzqG() {
      return this.zzaEU.zzqG();
   }

   public final void zzqH() {
      this.zzaEU.clear();
   }
}
