package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

final class zzbj implements PendingResult.zza {
   // $FF: synthetic field
   private PendingResult zzaIj;
   // $FF: synthetic field
   private TaskCompletionSource zzaIk;
   // $FF: synthetic field
   private zzbm zzaIl;
   // $FF: synthetic field
   private zzbn zzaIm;

   zzbj(PendingResult var1, TaskCompletionSource var2, zzbm var3, zzbn var4) {
      this.zzaIj = var1;
      this.zzaIk = var2;
      this.zzaIl = var3;
      this.zzaIm = var4;
      super();
   }

   public final void zzo(Status var1) {
      if (var1.isSuccess()) {
         Result var2 = this.zzaIj.await(0L, TimeUnit.MILLISECONDS);
         this.zzaIk.setResult(this.zzaIl.zzd(var2));
      } else {
         this.zzaIk.setException(this.zzaIm.zzy(var1));
      }
   }
}
