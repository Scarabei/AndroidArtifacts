package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class zzbgv implements Executor {
   private final Handler mHandler;

   public zzbgv(Looper var1) {
      this.mHandler = new Handler(var1);
   }

   public final void execute(@NonNull Runnable var1) {
      this.mHandler.post(var1);
   }
}
