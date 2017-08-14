package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zzcuu extends ThreadPoolExecutor {
   private final Context mContext;

   public zzcuu(Context var1, int var2, int var3, long var4, TimeUnit var6, BlockingQueue var7, ThreadFactory var8) {
      super(1, 1, 0L, var6, var7, var8);
      this.mContext = var1;
   }

   protected final void afterExecute(Runnable var1, Throwable var2) {
      if (var2 != null) {
         zzcup.zza("Uncaught exception: ", var2, this.mContext);
      }

   }
}
