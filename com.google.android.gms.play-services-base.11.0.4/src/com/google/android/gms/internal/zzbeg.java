package com.google.android.gms.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zzbeg {
   private static final ExecutorService zzaEb;

   public static ExecutorService zzqj() {
      return zzaEb;
   }

   static {
      zzaEb = new ThreadPoolExecutor(0, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new zzbgw("GAC_Transform"));
   }
}
