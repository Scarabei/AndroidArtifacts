package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzagy implements ThreadFactory {
   private final AtomicInteger zzXJ;
   // $FF: synthetic field
   private String zzZq;

   zzagy(String var1) {
      this.zzZq = var1;
      super();
      this.zzXJ = new AtomicInteger(1);
   }

   public final Thread newThread(Runnable var1) {
      String var2 = this.zzZq;
      int var3 = this.zzXJ.getAndIncrement();
      return new Thread(var1, (new StringBuilder(23 + String.valueOf(var2).length())).append("AdWorker(").append(var2).append(") #").append(var3).toString());
   }
}
