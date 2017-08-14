package com.google.android.gms.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzber;
import com.google.android.gms.internal.zzcru;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzk extends zzcru {
   // $FF: synthetic field
   private TaskCompletionSource zzaCV;

   zzk(zzj var1, TaskCompletionSource var2) {
      this.zzaCV = var2;
      super();
   }

   public final void zzF(Status var1) {
      TaskCompletionSource var2 = this.zzaCV;
      zzber.zza(var1, (Object)null, var2);
   }
}
