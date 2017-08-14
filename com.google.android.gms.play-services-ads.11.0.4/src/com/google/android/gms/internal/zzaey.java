package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzaey implements ThreadFactory {
   private final AtomicInteger zzXJ = new AtomicInteger(1);

   zzaey(zzaew var1) {
   }

   public final Thread newThread(Runnable var1) {
      int var2 = this.zzXJ.getAndIncrement();
      return new Thread(var1, (new StringBuilder(42)).append("AdWorker(SCION_TASK_EXECUTOR) #").append(var2).toString());
   }
}
