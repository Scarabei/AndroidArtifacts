package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzban extends zzbam {
   protected final TaskCompletionSource zzalE;

   public zzban(int var1, TaskCompletionSource var2) {
      super(var1);
      this.zzalE = var2;
   }

   public void zzp(@NonNull Status var1) {
      this.zzalE.trySetException(new ApiException(var1));
   }

   public void zza(@NonNull zzbbt var1, boolean var2) {
   }

   public final void zza(zzbdd var1) throws DeadObjectException {
      try {
         this.zzb(var1);
      } catch (DeadObjectException var3) {
         this.zzp(zzbam.zzb(var3));
         throw var3;
      } catch (RemoteException var4) {
         this.zzp(zzbam.zzb(var4));
      }
   }

   protected abstract void zzb(zzbdd var1) throws RemoteException;
}
