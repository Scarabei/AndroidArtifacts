package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbaq extends zzbam {
   private final zzbeq zzaBw;
   private final TaskCompletionSource zzalE;
   private final zzbem zzaBx;

   public zzbaq(int var1, zzbeq var2, TaskCompletionSource var3, zzbem var4) {
      super(var1);
      this.zzalE = var3;
      this.zzaBw = var2;
      this.zzaBx = var4;
   }

   public final void zza(zzbdd var1) throws DeadObjectException {
      try {
         this.zzaBw.zza(var1.zzpJ(), this.zzalE);
      } catch (DeadObjectException var3) {
         throw var3;
      } catch (RemoteException var4) {
         this.zzp(zzbam.zzb(var4));
      }
   }

   public final void zzp(@NonNull Status var1) {
      this.zzalE.trySetException(this.zzaBx.zzq(var1));
   }

   public final void zza(@NonNull zzbbt var1, boolean var2) {
      var1.zza(this.zzalE, var2);
   }
}
