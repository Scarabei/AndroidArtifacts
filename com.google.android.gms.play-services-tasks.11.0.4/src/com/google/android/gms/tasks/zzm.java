package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzm implements Executor {
   public final void execute(@NonNull Runnable var1) {
      var1.run();
   }
}
