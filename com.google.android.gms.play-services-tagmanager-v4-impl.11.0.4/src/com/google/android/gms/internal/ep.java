package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ep {
   private static Integer zzbLP = Integer.valueOf(0);
   private static Integer zzbLQ = Integer.valueOf(1);
   private final Context mContext;
   private final ExecutorService zzbrV;

   public ep(Context var1) {
      this(var1, Executors.newSingleThreadExecutor());
   }

   private ep(Context var1, ExecutorService var2) {
      this.mContext = var1;
      this.zzbrV = var2;
   }
}
