package com.google.android.gms.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzber {
   public static void zza(Status var0, Object var1, TaskCompletionSource var2) {
      if (var0.isSuccess()) {
         var2.setResult(var1);
      } else {
         var2.setException(new ApiException(var0));
      }
   }
}
