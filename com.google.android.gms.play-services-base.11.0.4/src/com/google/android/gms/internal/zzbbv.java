package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbbv implements OnCompleteListener {
   // $FF: synthetic field
   private TaskCompletionSource zzaCV;
   // $FF: synthetic field
   private zzbbt zzaCU;

   zzbbv(zzbbt var1, TaskCompletionSource var2) {
      this.zzaCU = var1;
      this.zzaCV = var2;
      super();
   }

   public final void onComplete(@NonNull Task var1) {
      zzbbt.zzb(this.zzaCU).remove(this.zzaCV);
   }
}
