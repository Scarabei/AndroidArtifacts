package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class cz {
   private final ScheduledExecutorService zzbFY;
   private ScheduledFuture zzbGa;
   private String zzbEa;
   private boolean mClosed;

   public cz() {
      this(Executors.newSingleThreadScheduledExecutor());
   }

   private cz(ScheduledExecutorService var1) {
      this.zzbGa = null;
      this.zzbEa = null;
      this.zzbFY = var1;
      this.mClosed = false;
   }

   public final void zza(Context var1, cl var2, long var3, cc var5) {
      synchronized(this) {
         if (this.zzbGa != null) {
            this.zzbGa.cancel(false);
         }

         cy var7 = new cy(var1, var2, var5);
         this.zzbGa = this.zzbFY.schedule(var7, 0L, TimeUnit.MILLISECONDS);
      }
   }
}
