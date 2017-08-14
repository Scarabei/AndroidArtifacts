package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class TaskExecutors {
   public static final Executor MAIN_THREAD = new TaskExecutors.zza();
   static final Executor zzbMf = new zzm();

   static final class zza implements Executor {
      private final Handler mHandler = new Handler(Looper.getMainLooper());

      public final void execute(@NonNull Runnable var1) {
         this.mHandler.post(var1);
      }
   }
}
