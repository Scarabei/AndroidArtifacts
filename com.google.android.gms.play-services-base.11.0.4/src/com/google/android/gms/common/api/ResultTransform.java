package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.internal.zzbeh;

public abstract class ResultTransform {
   @WorkerThread
   @Nullable
   public abstract PendingResult onSuccess(@NonNull Result var1);

   @NonNull
   public Status onFailure(@NonNull Status var1) {
      return var1;
   }

   @NonNull
   public final PendingResult createFailedResult(@NonNull Status var1) {
      return new zzbeh(var1);
   }
}
