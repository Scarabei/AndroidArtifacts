package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;

final class zzcxh implements ThreadFactory {
   public final Thread newThread(Runnable var1) {
      return new Thread(var1, "google-tag-manager-scheduler-thread");
   }
}
