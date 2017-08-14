package com.google.android.gms.gcm;

import android.support.annotation.NonNull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzb implements ThreadFactory {
   private final AtomicInteger zzbfH = new AtomicInteger(1);

   zzb(GcmTaskService var1) {
   }

   public final Thread newThread(@NonNull Runnable var1) {
      int var3 = this.zzbfH.getAndIncrement();
      Thread var2;
      (var2 = new Thread(var1, (new StringBuilder(20)).append("gcm-task#").append(var3).toString())).setPriority(4);
      return var2;
   }
}
